import psycopg2
import pandas as pd
import sys

DB_CONFIG = {
    'host': 'localhost',
    'port': 5432,
    'database': 'traffic_db',
    'user': 'bigdata',
    'password': 'bigdata123'
}

def load_data(csv_file):
    print(f"Loading {csv_file}...")
    
    df = pd.read_csv(csv_file, sep=';', encoding='utf-8')
    print(f"Found {len(df)} rows")
    
    df.rename(columns={
        'Identifiant arc': 'identifiant_arc',
        'Libelle': 'libelle',
        'Date et heure de comptage': 'date_heure_comptage',
        'Débit horaire': 'debit_horaire',
        'Taux d\'occupation': 'taux_occupation',
        'Etat trafic': 'etat_trafic',
        'Identifiant noeud amont': 'identifiant_noeud_amont',
        'Libelle noeud amont': 'libelle_noeud_amont',
        'Identifiant noeud aval': 'identifiant_noeud_aval',
        'Libelle noeud aval': 'libelle_noeud_aval',
        'Etat arc': 'etat_arc',
        'Date debut dispo data': 'date_debut_dispo_data',
        'Date fin dispo data': 'date_fin_dispo_data',
        'geo_point_2d': 'geo_point'
    }, inplace=True)
    
    df['date_heure_comptage'] = pd.to_datetime(df['date_heure_comptage'], errors='coerce')
    df['date_debut_dispo_data'] = pd.to_datetime(df['date_debut_dispo_data'], errors='coerce')
    df['date_fin_dispo_data'] = pd.to_datetime(df['date_fin_dispo_data'], errors='coerce')
    
    coords = df['geo_point'].str.split(',', expand=True)
    df['latitude'] = pd.to_numeric(coords[0], errors='coerce')
    df['longitude'] = pd.to_numeric(coords[1], errors='coerce')
    
    df['debit_horaire'] = pd.to_numeric(df['debit_horaire'], errors='coerce')
    df['taux_occupation'] = pd.to_numeric(df['taux_occupation'], errors='coerce')
    
    columns = ['identifiant_arc', 'libelle', 'date_heure_comptage', 'debit_horaire',
               'taux_occupation', 'etat_trafic', 'identifiant_noeud_amont',
               'libelle_noeud_amont', 'identifiant_noeud_aval', 'libelle_noeud_aval',
               'etat_arc', 'date_debut_dispo_data', 'date_fin_dispo_data',
               'latitude', 'longitude']
    
    df = df[columns]
    
    print("Connecting to database...")
    conn = psycopg2.connect(**DB_CONFIG)
    cursor = conn.cursor()
    
    print("Inserting data...")
    for idx, row in df.iterrows():
        cursor.execute("""
            INSERT INTO traffic_data (
                identifiant_arc, libelle, date_heure_comptage, debit_horaire,
                taux_occupation, etat_trafic, identifiant_noeud_amont,
                libelle_noeud_amont, identifiant_noeud_aval, libelle_noeud_aval,
                etat_arc, date_debut_dispo_data, date_fin_dispo_data,
                latitude, longitude
            ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        """, tuple(row))
        
        if (idx + 1) % 1000 == 0:
            conn.commit()
            print(f"  {idx + 1} rows inserted...")
    
    conn.commit()
    cursor.close()
    conn.close()
    
    print(f"Done! Loaded {len(df)} rows")

if __name__ == "__main__":
    csv_file = sys.argv[1] if len(sys.argv) > 1 else "./data/paris_traffic_data.csv"
    load_data(csv_file)

