-- Create schema for Paris traffic data
-- Based on: Comptages routiers permanents - Open Data Paris

CREATE TABLE IF NOT EXISTS traffic_data (
    id SERIAL PRIMARY KEY,
    identifiant_arc VARCHAR(50),
    libelle VARCHAR(255),
    date_heure_comptage TIMESTAMP,
    debit_horaire DECIMAL(10,2),
    taux_occupation DECIMAL(10,5),
    etat_trafic VARCHAR(50),
    identifiant_noeud_amont VARCHAR(50),
    libelle_noeud_amont VARCHAR(255),
    identifiant_noeud_aval VARCHAR(50),
    libelle_noeud_aval VARCHAR(255),
    etat_arc VARCHAR(50),
    date_debut_dispo_data DATE,
    date_fin_dispo_data DATE,
    latitude DECIMAL(10,8),
    longitude DECIMAL(10,8),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better query performance
CREATE INDEX idx_date_heure ON traffic_data(date_heure_comptage);
CREATE INDEX idx_arc ON traffic_data(identifiant_arc);
CREATE INDEX idx_etat_trafic ON traffic_data(etat_trafic);

-- Grant permissions
GRANT ALL PRIVILEGES ON DATABASE traffic_db TO bigdata;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO bigdata;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO bigdata;

