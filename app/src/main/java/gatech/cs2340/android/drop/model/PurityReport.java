package gatech.cs2340.android.drop.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BobZhai on 3/7/17.
 */

public class PurityReport implements Parcelable {
    private String _date;
    private static int _no;
    private String _name;
    private String _latitude;
    private String _longitude;
    private Condition _condition;
    private String _virus;
    private String contaminant;



    public PurityReport(String _date, String _name, String _longitude, String _latitude, String _virus, String contaminant, Condition _condition) {
        this._date = _date;
        this._name = _name;
        this._longitude = _longitude;
        this._latitude = _latitude;
        this._virus = _virus;
        this.contaminant = contaminant;
        this._condition = _condition;
    }


    public String get_date() {
        return _date;
    }

    public String get_name() {
        return _name;
    }

    public String get_latitude() {
        return _latitude;
    }

    public String get_longitude() {
        return _longitude;
    }

    public Condition get_condition() {
        return _condition;
    }

    public String get_virus() {
        return _virus;
    }

    public String getContaminant() {
        return contaminant;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_latitude(String _latitude) {
        this._latitude = _latitude;
    }

    public void set_longitude(String _longitude) {
        this._longitude = _longitude;
    }

    public void set_condition(Condition _condition) {
        this._condition = _condition;
    }

    public void set_virus(String _virus) {
        this._virus = _virus;
    }

    public void set_Contaminant(String contaminant) {
        this.contaminant = contaminant;
    }

    private PurityReport(Parcel in) {
        _date = in.readString();
        _no = in.readInt();
        _name = in.readString();
        _latitude = in.readString();
        _longitude = in.readString();
        _condition = (Condition) in.readSerializable();
        _virus = in.readString();
        contaminant = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    /* *************************
       If you add new instance vars to Student, you will need to add them to the write
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_date);
        dest.writeInt(_no);
        dest.writeString(_name );
        dest.writeString(_latitude);
        dest.writeString(_longitude);
        dest.writeSerializable(_condition);
        dest.writeString(_virus);
        dest.writeString(contaminant);
    }

    public static final Parcelable.Creator<PurityReport> CREATOR
            = new Parcelable.Creator<PurityReport>() {
        public PurityReport createFromParcel(Parcel in) {
            return new PurityReport(in);
        }

        public PurityReport[] newArray(int size) {
            return new PurityReport[size];
        }
    };
}
