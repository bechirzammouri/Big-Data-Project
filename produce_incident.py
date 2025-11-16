#!/usr/bin/python3
# producer_incidents.py - Alternative using Traffic Incidents API

import os 
import time
import requests
from kafka import KafkaProducer
import json
from datetime import datetime

# TomTom Traffic Incidents API
base_url = "api.tomtom.com"
API_KEY = os.getenv("TOMTOM_API_KEY")  # Replace with your actual API key
TOPIC = "traffic_events"
POLL_INTERVAL = 60  # seconds

# Paris bounding box coordinates
BBOX = "2.2241,48.8155,2.4699,48.9022"  # minLon,minLat,maxLon,maxLat

# Initialize Kafka producer
producer = KafkaProducer(
    bootstrap_servers='localhost:9092',
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

def fetch_traffic_incidents():
    """
    Fetch real-time traffic incidents for Paris area
    This uses the Traffic Incident Details API (simpler, no POST required)
    """
    try:
        url = f"https://{base_url}/traffic/services/5/incidentDetails"
        
        params = {
            "key": API_KEY,
            "bbox": BBOX,
            "fields": "{incidents{type,geometry{type,coordinates},properties{id,iconCategory,magnitudeOfDelay,events{description,code,iconCategory},startTime,endTime,from,to,length,delay,roadNumbers,timeValidity}}}",
            "language": "en-GB",
            "categoryFilter": "0,1,2,3,4,5,6,7,8,9,10,11,14",
            "timeValidityFilter": "present"
        }
        
        response = requests.get(url, params=params, timeout=10)
        
        if response.status_code == 200:
            print(f"✓ Successfully fetched incidents at {datetime.now()}")
            return response.json()
        else:
            print(f"✗ API error: {response.status_code}")
            print(f"Response: {response.text}")
            return None
            
    except Exception as e:
        print(f"✗ Exception while fetching API: {e}")
        return None

def send_incidents_to_kafka(data):
    """
    Extract incidents and send each to Kafka
    """
    if not data:
        print("No data to send")
        return
    
    try:
        incidents = data.get("incidents", [])
        
        if not incidents:
            print("No incidents found in the response")
            return
        
        events_sent = 0
        for incident in incidents:
            # Extract relevant information
            props = incident.get("properties", {})
            event = {
                "id": props.get("id"),
                "type": props.get("iconCategory"),
                "description": props.get("events", [{}])[0].get("description", ""),
                "location": {
                    "from": props.get("from"),
                    "to": props.get("to"),
                    "coordinates": incident.get("geometry", {}).get("coordinates")
                },
                "severity": props.get("magnitudeOfDelay", 0),
                "delay": props.get("delay", 0),
                "length": props.get("length", 0),
                "roadNumbers": props.get("roadNumbers", []),
                "timestamp": datetime.now().isoformat(),
                "start_time": props.get("startTime"),
                "end_time": props.get("endTime")
            }
            
            producer.send(TOPIC, value=event)
            events_sent += 1
        
        producer.flush()
        print(f" Sent {events_sent} incident(s) to Kafka topic '{TOPIC}'")
        
    except Exception as e:
        print(f"✗ Error processing incidents: {e}")

if __name__ == "__main__":
    print("=" * 60)
    print("Traffic Incidents Producer (TomTom API → Kafka)")
    print("=" * 60)
    print(f"Kafka broker: localhost:9092")
    print(f"Kafka topic: {TOPIC}")
    print(f"Poll interval: {POLL_INTERVAL} seconds")
    print(f"Location: Paris, France")
    print("=" * 60)
    
    if API_KEY == "YOUR_ACTUAL_TOMTOM_API_KEY":
        print("\n ERROR: Please set your actual TomTom API key!")
        print("\nSteps to get an API key:")
        print("1. Visit: https://developer.tomtom.com/")
        print("2. Sign up or log in")
        print("3. Go to 'My Dashboard' → 'API Keys'")
        print("4. Create a new key with 'Traffic API' permissions")
        print("5. Copy the key and replace it in this script")
        exit(1)
    
    print("\nStarting polling loop...\n")
    
    while True:
        print(f"[{datetime.now().strftime('%Y-%m-%d %H:%M:%S')}] Fetching traffic incidents...")
        data = fetch_traffic_incidents()
        
        if data:
            send_incidents_to_kafka(data)
        else:
            print("Failed to fetch data, will retry...")
        
        print(f"Waiting {POLL_INTERVAL} seconds until next poll...\n")
        time.sleep(POLL_INTERVAL)

