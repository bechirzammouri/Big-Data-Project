// ORM class for table 'traffic_data'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sun Nov 16 20:38:19 UTC 2025
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class traffic_data extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.id = (Integer)value;
      }
    });
    setters.put("identifiant_arc", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.identifiant_arc = (String)value;
      }
    });
    setters.put("libelle", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.libelle = (String)value;
      }
    });
    setters.put("date_heure_comptage", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.date_heure_comptage = (java.sql.Timestamp)value;
      }
    });
    setters.put("debit_horaire", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.debit_horaire = (java.math.BigDecimal)value;
      }
    });
    setters.put("taux_occupation", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.taux_occupation = (java.math.BigDecimal)value;
      }
    });
    setters.put("etat_trafic", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.etat_trafic = (String)value;
      }
    });
    setters.put("identifiant_noeud_amont", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.identifiant_noeud_amont = (String)value;
      }
    });
    setters.put("libelle_noeud_amont", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.libelle_noeud_amont = (String)value;
      }
    });
    setters.put("identifiant_noeud_aval", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.identifiant_noeud_aval = (String)value;
      }
    });
    setters.put("libelle_noeud_aval", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.libelle_noeud_aval = (String)value;
      }
    });
    setters.put("etat_arc", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.etat_arc = (String)value;
      }
    });
    setters.put("date_debut_dispo_data", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.date_debut_dispo_data = (java.sql.Date)value;
      }
    });
    setters.put("date_fin_dispo_data", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.date_fin_dispo_data = (java.sql.Date)value;
      }
    });
    setters.put("latitude", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.latitude = (java.math.BigDecimal)value;
      }
    });
    setters.put("longitude", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.longitude = (java.math.BigDecimal)value;
      }
    });
    setters.put("created_at", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        traffic_data.this.created_at = (java.sql.Timestamp)value;
      }
    });
  }
  public traffic_data() {
    init0();
  }
  private Integer id;
  public Integer get_id() {
    return id;
  }
  public void set_id(Integer id) {
    this.id = id;
  }
  public traffic_data with_id(Integer id) {
    this.id = id;
    return this;
  }
  private String identifiant_arc;
  public String get_identifiant_arc() {
    return identifiant_arc;
  }
  public void set_identifiant_arc(String identifiant_arc) {
    this.identifiant_arc = identifiant_arc;
  }
  public traffic_data with_identifiant_arc(String identifiant_arc) {
    this.identifiant_arc = identifiant_arc;
    return this;
  }
  private String libelle;
  public String get_libelle() {
    return libelle;
  }
  public void set_libelle(String libelle) {
    this.libelle = libelle;
  }
  public traffic_data with_libelle(String libelle) {
    this.libelle = libelle;
    return this;
  }
  private java.sql.Timestamp date_heure_comptage;
  public java.sql.Timestamp get_date_heure_comptage() {
    return date_heure_comptage;
  }
  public void set_date_heure_comptage(java.sql.Timestamp date_heure_comptage) {
    this.date_heure_comptage = date_heure_comptage;
  }
  public traffic_data with_date_heure_comptage(java.sql.Timestamp date_heure_comptage) {
    this.date_heure_comptage = date_heure_comptage;
    return this;
  }
  private java.math.BigDecimal debit_horaire;
  public java.math.BigDecimal get_debit_horaire() {
    return debit_horaire;
  }
  public void set_debit_horaire(java.math.BigDecimal debit_horaire) {
    this.debit_horaire = debit_horaire;
  }
  public traffic_data with_debit_horaire(java.math.BigDecimal debit_horaire) {
    this.debit_horaire = debit_horaire;
    return this;
  }
  private java.math.BigDecimal taux_occupation;
  public java.math.BigDecimal get_taux_occupation() {
    return taux_occupation;
  }
  public void set_taux_occupation(java.math.BigDecimal taux_occupation) {
    this.taux_occupation = taux_occupation;
  }
  public traffic_data with_taux_occupation(java.math.BigDecimal taux_occupation) {
    this.taux_occupation = taux_occupation;
    return this;
  }
  private String etat_trafic;
  public String get_etat_trafic() {
    return etat_trafic;
  }
  public void set_etat_trafic(String etat_trafic) {
    this.etat_trafic = etat_trafic;
  }
  public traffic_data with_etat_trafic(String etat_trafic) {
    this.etat_trafic = etat_trafic;
    return this;
  }
  private String identifiant_noeud_amont;
  public String get_identifiant_noeud_amont() {
    return identifiant_noeud_amont;
  }
  public void set_identifiant_noeud_amont(String identifiant_noeud_amont) {
    this.identifiant_noeud_amont = identifiant_noeud_amont;
  }
  public traffic_data with_identifiant_noeud_amont(String identifiant_noeud_amont) {
    this.identifiant_noeud_amont = identifiant_noeud_amont;
    return this;
  }
  private String libelle_noeud_amont;
  public String get_libelle_noeud_amont() {
    return libelle_noeud_amont;
  }
  public void set_libelle_noeud_amont(String libelle_noeud_amont) {
    this.libelle_noeud_amont = libelle_noeud_amont;
  }
  public traffic_data with_libelle_noeud_amont(String libelle_noeud_amont) {
    this.libelle_noeud_amont = libelle_noeud_amont;
    return this;
  }
  private String identifiant_noeud_aval;
  public String get_identifiant_noeud_aval() {
    return identifiant_noeud_aval;
  }
  public void set_identifiant_noeud_aval(String identifiant_noeud_aval) {
    this.identifiant_noeud_aval = identifiant_noeud_aval;
  }
  public traffic_data with_identifiant_noeud_aval(String identifiant_noeud_aval) {
    this.identifiant_noeud_aval = identifiant_noeud_aval;
    return this;
  }
  private String libelle_noeud_aval;
  public String get_libelle_noeud_aval() {
    return libelle_noeud_aval;
  }
  public void set_libelle_noeud_aval(String libelle_noeud_aval) {
    this.libelle_noeud_aval = libelle_noeud_aval;
  }
  public traffic_data with_libelle_noeud_aval(String libelle_noeud_aval) {
    this.libelle_noeud_aval = libelle_noeud_aval;
    return this;
  }
  private String etat_arc;
  public String get_etat_arc() {
    return etat_arc;
  }
  public void set_etat_arc(String etat_arc) {
    this.etat_arc = etat_arc;
  }
  public traffic_data with_etat_arc(String etat_arc) {
    this.etat_arc = etat_arc;
    return this;
  }
  private java.sql.Date date_debut_dispo_data;
  public java.sql.Date get_date_debut_dispo_data() {
    return date_debut_dispo_data;
  }
  public void set_date_debut_dispo_data(java.sql.Date date_debut_dispo_data) {
    this.date_debut_dispo_data = date_debut_dispo_data;
  }
  public traffic_data with_date_debut_dispo_data(java.sql.Date date_debut_dispo_data) {
    this.date_debut_dispo_data = date_debut_dispo_data;
    return this;
  }
  private java.sql.Date date_fin_dispo_data;
  public java.sql.Date get_date_fin_dispo_data() {
    return date_fin_dispo_data;
  }
  public void set_date_fin_dispo_data(java.sql.Date date_fin_dispo_data) {
    this.date_fin_dispo_data = date_fin_dispo_data;
  }
  public traffic_data with_date_fin_dispo_data(java.sql.Date date_fin_dispo_data) {
    this.date_fin_dispo_data = date_fin_dispo_data;
    return this;
  }
  private java.math.BigDecimal latitude;
  public java.math.BigDecimal get_latitude() {
    return latitude;
  }
  public void set_latitude(java.math.BigDecimal latitude) {
    this.latitude = latitude;
  }
  public traffic_data with_latitude(java.math.BigDecimal latitude) {
    this.latitude = latitude;
    return this;
  }
  private java.math.BigDecimal longitude;
  public java.math.BigDecimal get_longitude() {
    return longitude;
  }
  public void set_longitude(java.math.BigDecimal longitude) {
    this.longitude = longitude;
  }
  public traffic_data with_longitude(java.math.BigDecimal longitude) {
    this.longitude = longitude;
    return this;
  }
  private java.sql.Timestamp created_at;
  public java.sql.Timestamp get_created_at() {
    return created_at;
  }
  public void set_created_at(java.sql.Timestamp created_at) {
    this.created_at = created_at;
  }
  public traffic_data with_created_at(java.sql.Timestamp created_at) {
    this.created_at = created_at;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof traffic_data)) {
      return false;
    }
    traffic_data that = (traffic_data) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.identifiant_arc == null ? that.identifiant_arc == null : this.identifiant_arc.equals(that.identifiant_arc));
    equal = equal && (this.libelle == null ? that.libelle == null : this.libelle.equals(that.libelle));
    equal = equal && (this.date_heure_comptage == null ? that.date_heure_comptage == null : this.date_heure_comptage.equals(that.date_heure_comptage));
    equal = equal && (this.debit_horaire == null ? that.debit_horaire == null : this.debit_horaire.equals(that.debit_horaire));
    equal = equal && (this.taux_occupation == null ? that.taux_occupation == null : this.taux_occupation.equals(that.taux_occupation));
    equal = equal && (this.etat_trafic == null ? that.etat_trafic == null : this.etat_trafic.equals(that.etat_trafic));
    equal = equal && (this.identifiant_noeud_amont == null ? that.identifiant_noeud_amont == null : this.identifiant_noeud_amont.equals(that.identifiant_noeud_amont));
    equal = equal && (this.libelle_noeud_amont == null ? that.libelle_noeud_amont == null : this.libelle_noeud_amont.equals(that.libelle_noeud_amont));
    equal = equal && (this.identifiant_noeud_aval == null ? that.identifiant_noeud_aval == null : this.identifiant_noeud_aval.equals(that.identifiant_noeud_aval));
    equal = equal && (this.libelle_noeud_aval == null ? that.libelle_noeud_aval == null : this.libelle_noeud_aval.equals(that.libelle_noeud_aval));
    equal = equal && (this.etat_arc == null ? that.etat_arc == null : this.etat_arc.equals(that.etat_arc));
    equal = equal && (this.date_debut_dispo_data == null ? that.date_debut_dispo_data == null : this.date_debut_dispo_data.equals(that.date_debut_dispo_data));
    equal = equal && (this.date_fin_dispo_data == null ? that.date_fin_dispo_data == null : this.date_fin_dispo_data.equals(that.date_fin_dispo_data));
    equal = equal && (this.latitude == null ? that.latitude == null : this.latitude.equals(that.latitude));
    equal = equal && (this.longitude == null ? that.longitude == null : this.longitude.equals(that.longitude));
    equal = equal && (this.created_at == null ? that.created_at == null : this.created_at.equals(that.created_at));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof traffic_data)) {
      return false;
    }
    traffic_data that = (traffic_data) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.identifiant_arc == null ? that.identifiant_arc == null : this.identifiant_arc.equals(that.identifiant_arc));
    equal = equal && (this.libelle == null ? that.libelle == null : this.libelle.equals(that.libelle));
    equal = equal && (this.date_heure_comptage == null ? that.date_heure_comptage == null : this.date_heure_comptage.equals(that.date_heure_comptage));
    equal = equal && (this.debit_horaire == null ? that.debit_horaire == null : this.debit_horaire.equals(that.debit_horaire));
    equal = equal && (this.taux_occupation == null ? that.taux_occupation == null : this.taux_occupation.equals(that.taux_occupation));
    equal = equal && (this.etat_trafic == null ? that.etat_trafic == null : this.etat_trafic.equals(that.etat_trafic));
    equal = equal && (this.identifiant_noeud_amont == null ? that.identifiant_noeud_amont == null : this.identifiant_noeud_amont.equals(that.identifiant_noeud_amont));
    equal = equal && (this.libelle_noeud_amont == null ? that.libelle_noeud_amont == null : this.libelle_noeud_amont.equals(that.libelle_noeud_amont));
    equal = equal && (this.identifiant_noeud_aval == null ? that.identifiant_noeud_aval == null : this.identifiant_noeud_aval.equals(that.identifiant_noeud_aval));
    equal = equal && (this.libelle_noeud_aval == null ? that.libelle_noeud_aval == null : this.libelle_noeud_aval.equals(that.libelle_noeud_aval));
    equal = equal && (this.etat_arc == null ? that.etat_arc == null : this.etat_arc.equals(that.etat_arc));
    equal = equal && (this.date_debut_dispo_data == null ? that.date_debut_dispo_data == null : this.date_debut_dispo_data.equals(that.date_debut_dispo_data));
    equal = equal && (this.date_fin_dispo_data == null ? that.date_fin_dispo_data == null : this.date_fin_dispo_data.equals(that.date_fin_dispo_data));
    equal = equal && (this.latitude == null ? that.latitude == null : this.latitude.equals(that.latitude));
    equal = equal && (this.longitude == null ? that.longitude == null : this.longitude.equals(that.longitude));
    equal = equal && (this.created_at == null ? that.created_at == null : this.created_at.equals(that.created_at));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.identifiant_arc = JdbcWritableBridge.readString(2, __dbResults);
    this.libelle = JdbcWritableBridge.readString(3, __dbResults);
    this.date_heure_comptage = JdbcWritableBridge.readTimestamp(4, __dbResults);
    this.debit_horaire = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.taux_occupation = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.etat_trafic = JdbcWritableBridge.readString(7, __dbResults);
    this.identifiant_noeud_amont = JdbcWritableBridge.readString(8, __dbResults);
    this.libelle_noeud_amont = JdbcWritableBridge.readString(9, __dbResults);
    this.identifiant_noeud_aval = JdbcWritableBridge.readString(10, __dbResults);
    this.libelle_noeud_aval = JdbcWritableBridge.readString(11, __dbResults);
    this.etat_arc = JdbcWritableBridge.readString(12, __dbResults);
    this.date_debut_dispo_data = JdbcWritableBridge.readDate(13, __dbResults);
    this.date_fin_dispo_data = JdbcWritableBridge.readDate(14, __dbResults);
    this.latitude = JdbcWritableBridge.readBigDecimal(15, __dbResults);
    this.longitude = JdbcWritableBridge.readBigDecimal(16, __dbResults);
    this.created_at = JdbcWritableBridge.readTimestamp(17, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.identifiant_arc = JdbcWritableBridge.readString(2, __dbResults);
    this.libelle = JdbcWritableBridge.readString(3, __dbResults);
    this.date_heure_comptage = JdbcWritableBridge.readTimestamp(4, __dbResults);
    this.debit_horaire = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.taux_occupation = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.etat_trafic = JdbcWritableBridge.readString(7, __dbResults);
    this.identifiant_noeud_amont = JdbcWritableBridge.readString(8, __dbResults);
    this.libelle_noeud_amont = JdbcWritableBridge.readString(9, __dbResults);
    this.identifiant_noeud_aval = JdbcWritableBridge.readString(10, __dbResults);
    this.libelle_noeud_aval = JdbcWritableBridge.readString(11, __dbResults);
    this.etat_arc = JdbcWritableBridge.readString(12, __dbResults);
    this.date_debut_dispo_data = JdbcWritableBridge.readDate(13, __dbResults);
    this.date_fin_dispo_data = JdbcWritableBridge.readDate(14, __dbResults);
    this.latitude = JdbcWritableBridge.readBigDecimal(15, __dbResults);
    this.longitude = JdbcWritableBridge.readBigDecimal(16, __dbResults);
    this.created_at = JdbcWritableBridge.readTimestamp(17, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(identifiant_arc, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(libelle, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(date_heure_comptage, 4 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(debit_horaire, 5 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(taux_occupation, 6 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(etat_trafic, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(identifiant_noeud_amont, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(libelle_noeud_amont, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(identifiant_noeud_aval, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(libelle_noeud_aval, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(etat_arc, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDate(date_debut_dispo_data, 13 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeDate(date_fin_dispo_data, 14 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(latitude, 15 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(longitude, 16 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeTimestamp(created_at, 17 + __off, 93, __dbStmt);
    return 17;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(identifiant_arc, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(libelle, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(date_heure_comptage, 4 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(debit_horaire, 5 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(taux_occupation, 6 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(etat_trafic, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(identifiant_noeud_amont, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(libelle_noeud_amont, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(identifiant_noeud_aval, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(libelle_noeud_aval, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(etat_arc, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDate(date_debut_dispo_data, 13 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeDate(date_fin_dispo_data, 14 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(latitude, 15 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(longitude, 16 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeTimestamp(created_at, 17 + __off, 93, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id = null;
    } else {
    this.id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.identifiant_arc = null;
    } else {
    this.identifiant_arc = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.libelle = null;
    } else {
    this.libelle = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.date_heure_comptage = null;
    } else {
    this.date_heure_comptage = new Timestamp(__dataIn.readLong());
    this.date_heure_comptage.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.debit_horaire = null;
    } else {
    this.debit_horaire = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.taux_occupation = null;
    } else {
    this.taux_occupation = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.etat_trafic = null;
    } else {
    this.etat_trafic = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.identifiant_noeud_amont = null;
    } else {
    this.identifiant_noeud_amont = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.libelle_noeud_amont = null;
    } else {
    this.libelle_noeud_amont = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.identifiant_noeud_aval = null;
    } else {
    this.identifiant_noeud_aval = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.libelle_noeud_aval = null;
    } else {
    this.libelle_noeud_aval = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.etat_arc = null;
    } else {
    this.etat_arc = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.date_debut_dispo_data = null;
    } else {
    this.date_debut_dispo_data = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.date_fin_dispo_data = null;
    } else {
    this.date_fin_dispo_data = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.latitude = null;
    } else {
    this.latitude = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.longitude = null;
    } else {
    this.longitude = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.created_at = null;
    } else {
    this.created_at = new Timestamp(__dataIn.readLong());
    this.created_at.setNanos(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id);
    }
    if (null == this.identifiant_arc) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, identifiant_arc);
    }
    if (null == this.libelle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, libelle);
    }
    if (null == this.date_heure_comptage) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_heure_comptage.getTime());
    __dataOut.writeInt(this.date_heure_comptage.getNanos());
    }
    if (null == this.debit_horaire) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.debit_horaire, __dataOut);
    }
    if (null == this.taux_occupation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.taux_occupation, __dataOut);
    }
    if (null == this.etat_trafic) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, etat_trafic);
    }
    if (null == this.identifiant_noeud_amont) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, identifiant_noeud_amont);
    }
    if (null == this.libelle_noeud_amont) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, libelle_noeud_amont);
    }
    if (null == this.identifiant_noeud_aval) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, identifiant_noeud_aval);
    }
    if (null == this.libelle_noeud_aval) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, libelle_noeud_aval);
    }
    if (null == this.etat_arc) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, etat_arc);
    }
    if (null == this.date_debut_dispo_data) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_debut_dispo_data.getTime());
    }
    if (null == this.date_fin_dispo_data) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_fin_dispo_data.getTime());
    }
    if (null == this.latitude) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.latitude, __dataOut);
    }
    if (null == this.longitude) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.longitude, __dataOut);
    }
    if (null == this.created_at) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.created_at.getTime());
    __dataOut.writeInt(this.created_at.getNanos());
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id);
    }
    if (null == this.identifiant_arc) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, identifiant_arc);
    }
    if (null == this.libelle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, libelle);
    }
    if (null == this.date_heure_comptage) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_heure_comptage.getTime());
    __dataOut.writeInt(this.date_heure_comptage.getNanos());
    }
    if (null == this.debit_horaire) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.debit_horaire, __dataOut);
    }
    if (null == this.taux_occupation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.taux_occupation, __dataOut);
    }
    if (null == this.etat_trafic) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, etat_trafic);
    }
    if (null == this.identifiant_noeud_amont) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, identifiant_noeud_amont);
    }
    if (null == this.libelle_noeud_amont) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, libelle_noeud_amont);
    }
    if (null == this.identifiant_noeud_aval) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, identifiant_noeud_aval);
    }
    if (null == this.libelle_noeud_aval) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, libelle_noeud_aval);
    }
    if (null == this.etat_arc) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, etat_arc);
    }
    if (null == this.date_debut_dispo_data) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_debut_dispo_data.getTime());
    }
    if (null == this.date_fin_dispo_data) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_fin_dispo_data.getTime());
    }
    if (null == this.latitude) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.latitude, __dataOut);
    }
    if (null == this.longitude) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.longitude, __dataOut);
    }
    if (null == this.created_at) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.created_at.getTime());
    __dataOut.writeInt(this.created_at.getNanos());
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(identifiant_arc==null?"null":identifiant_arc, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(libelle==null?"null":libelle, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_heure_comptage==null?"null":"" + date_heure_comptage, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(debit_horaire==null?"null":debit_horaire.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(taux_occupation==null?"null":taux_occupation.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(etat_trafic==null?"null":etat_trafic, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(identifiant_noeud_amont==null?"null":identifiant_noeud_amont, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(libelle_noeud_amont==null?"null":libelle_noeud_amont, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(identifiant_noeud_aval==null?"null":identifiant_noeud_aval, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(libelle_noeud_aval==null?"null":libelle_noeud_aval, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(etat_arc==null?"null":etat_arc, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_debut_dispo_data==null?"null":"" + date_debut_dispo_data, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_fin_dispo_data==null?"null":"" + date_fin_dispo_data, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(latitude==null?"null":latitude.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(longitude==null?"null":longitude.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(created_at==null?"null":"" + created_at, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(identifiant_arc==null?"null":identifiant_arc, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(libelle==null?"null":libelle, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_heure_comptage==null?"null":"" + date_heure_comptage, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(debit_horaire==null?"null":debit_horaire.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(taux_occupation==null?"null":taux_occupation.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(etat_trafic==null?"null":etat_trafic, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(identifiant_noeud_amont==null?"null":identifiant_noeud_amont, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(libelle_noeud_amont==null?"null":libelle_noeud_amont, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(identifiant_noeud_aval==null?"null":identifiant_noeud_aval, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(libelle_noeud_aval==null?"null":libelle_noeud_aval, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(etat_arc==null?"null":etat_arc, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_debut_dispo_data==null?"null":"" + date_debut_dispo_data, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_fin_dispo_data==null?"null":"" + date_fin_dispo_data, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(latitude==null?"null":latitude.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(longitude==null?"null":longitude.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(created_at==null?"null":"" + created_at, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.identifiant_arc = null; } else {
      this.identifiant_arc = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.libelle = null; } else {
      this.libelle = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date_heure_comptage = null; } else {
      this.date_heure_comptage = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.debit_horaire = null; } else {
      this.debit_horaire = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.taux_occupation = null; } else {
      this.taux_occupation = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.etat_trafic = null; } else {
      this.etat_trafic = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.identifiant_noeud_amont = null; } else {
      this.identifiant_noeud_amont = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.libelle_noeud_amont = null; } else {
      this.libelle_noeud_amont = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.identifiant_noeud_aval = null; } else {
      this.identifiant_noeud_aval = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.libelle_noeud_aval = null; } else {
      this.libelle_noeud_aval = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.etat_arc = null; } else {
      this.etat_arc = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date_debut_dispo_data = null; } else {
      this.date_debut_dispo_data = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date_fin_dispo_data = null; } else {
      this.date_fin_dispo_data = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.latitude = null; } else {
      this.latitude = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.longitude = null; } else {
      this.longitude = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.created_at = null; } else {
      this.created_at = java.sql.Timestamp.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.identifiant_arc = null; } else {
      this.identifiant_arc = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.libelle = null; } else {
      this.libelle = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date_heure_comptage = null; } else {
      this.date_heure_comptage = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.debit_horaire = null; } else {
      this.debit_horaire = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.taux_occupation = null; } else {
      this.taux_occupation = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.etat_trafic = null; } else {
      this.etat_trafic = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.identifiant_noeud_amont = null; } else {
      this.identifiant_noeud_amont = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.libelle_noeud_amont = null; } else {
      this.libelle_noeud_amont = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.identifiant_noeud_aval = null; } else {
      this.identifiant_noeud_aval = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.libelle_noeud_aval = null; } else {
      this.libelle_noeud_aval = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.etat_arc = null; } else {
      this.etat_arc = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date_debut_dispo_data = null; } else {
      this.date_debut_dispo_data = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date_fin_dispo_data = null; } else {
      this.date_fin_dispo_data = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.latitude = null; } else {
      this.latitude = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.longitude = null; } else {
      this.longitude = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.created_at = null; } else {
      this.created_at = java.sql.Timestamp.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    traffic_data o = (traffic_data) super.clone();
    o.date_heure_comptage = (o.date_heure_comptage != null) ? (java.sql.Timestamp) o.date_heure_comptage.clone() : null;
    o.date_debut_dispo_data = (o.date_debut_dispo_data != null) ? (java.sql.Date) o.date_debut_dispo_data.clone() : null;
    o.date_fin_dispo_data = (o.date_fin_dispo_data != null) ? (java.sql.Date) o.date_fin_dispo_data.clone() : null;
    o.created_at = (o.created_at != null) ? (java.sql.Timestamp) o.created_at.clone() : null;
    return o;
  }

  public void clone0(traffic_data o) throws CloneNotSupportedException {
    o.date_heure_comptage = (o.date_heure_comptage != null) ? (java.sql.Timestamp) o.date_heure_comptage.clone() : null;
    o.date_debut_dispo_data = (o.date_debut_dispo_data != null) ? (java.sql.Date) o.date_debut_dispo_data.clone() : null;
    o.date_fin_dispo_data = (o.date_fin_dispo_data != null) ? (java.sql.Date) o.date_fin_dispo_data.clone() : null;
    o.created_at = (o.created_at != null) ? (java.sql.Timestamp) o.created_at.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("identifiant_arc", this.identifiant_arc);
    __sqoop$field_map.put("libelle", this.libelle);
    __sqoop$field_map.put("date_heure_comptage", this.date_heure_comptage);
    __sqoop$field_map.put("debit_horaire", this.debit_horaire);
    __sqoop$field_map.put("taux_occupation", this.taux_occupation);
    __sqoop$field_map.put("etat_trafic", this.etat_trafic);
    __sqoop$field_map.put("identifiant_noeud_amont", this.identifiant_noeud_amont);
    __sqoop$field_map.put("libelle_noeud_amont", this.libelle_noeud_amont);
    __sqoop$field_map.put("identifiant_noeud_aval", this.identifiant_noeud_aval);
    __sqoop$field_map.put("libelle_noeud_aval", this.libelle_noeud_aval);
    __sqoop$field_map.put("etat_arc", this.etat_arc);
    __sqoop$field_map.put("date_debut_dispo_data", this.date_debut_dispo_data);
    __sqoop$field_map.put("date_fin_dispo_data", this.date_fin_dispo_data);
    __sqoop$field_map.put("latitude", this.latitude);
    __sqoop$field_map.put("longitude", this.longitude);
    __sqoop$field_map.put("created_at", this.created_at);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("identifiant_arc", this.identifiant_arc);
    __sqoop$field_map.put("libelle", this.libelle);
    __sqoop$field_map.put("date_heure_comptage", this.date_heure_comptage);
    __sqoop$field_map.put("debit_horaire", this.debit_horaire);
    __sqoop$field_map.put("taux_occupation", this.taux_occupation);
    __sqoop$field_map.put("etat_trafic", this.etat_trafic);
    __sqoop$field_map.put("identifiant_noeud_amont", this.identifiant_noeud_amont);
    __sqoop$field_map.put("libelle_noeud_amont", this.libelle_noeud_amont);
    __sqoop$field_map.put("identifiant_noeud_aval", this.identifiant_noeud_aval);
    __sqoop$field_map.put("libelle_noeud_aval", this.libelle_noeud_aval);
    __sqoop$field_map.put("etat_arc", this.etat_arc);
    __sqoop$field_map.put("date_debut_dispo_data", this.date_debut_dispo_data);
    __sqoop$field_map.put("date_fin_dispo_data", this.date_fin_dispo_data);
    __sqoop$field_map.put("latitude", this.latitude);
    __sqoop$field_map.put("longitude", this.longitude);
    __sqoop$field_map.put("created_at", this.created_at);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
