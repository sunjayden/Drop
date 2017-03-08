package gatech.cs2340.android.drop.model;

import android.os.Parcel;
import android.os.Parcelable;


public class SourceReport implements Parcelable {

    private static int Next_Id = 0;
    private String _date;
    private int _id;
    private String _name;
    private String _location;
    private Type _type;     // THIS IS THE Type
    private Water _water;  // this is the condition

    // ADD an attribute


    /* **********************
     * Getters and setters
     */
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    //no setter for this.  id is a read only field
    public int getId() { return _id; }

    public String getLocation() {return _location;}
    public void setLocation(String loc){ _location = loc;}

    public String getDate() {
        return _date;
    }

    public void setDate(String date) {
        this._date = date;
    }

    public Type getType() {return _type;}
    public void setType(Type _type) {this._type = _type;}

    public Water getWater() {return _water;}
    public void setWater(Water water) {_water = water;}



    /**
     * Make a new student
     * @param name      the name of the reporter
     * @param loc     the location of water
     */
    public SourceReport(String name, Type loc) {

        this(name, loc, Water.WASTE);
    }

    /**
     *
     * @param name the name of the reporter
     * @param type water type
     * @param water water condition
     */

    public SourceReport(String name, Type type, Water water) {
        _name = name;
        _type = type;
        _id = SourceReport.Next_Id++;
        _water = water;

    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new sourceReport
     */
    public SourceReport() {
    }

    public static int findConditionPosition(Water water) {
        int i = 0;
        Water[] array = Water.values();
        while (i < array.length) {
            if (water.equals(array[i])) {
                i++;
            }
        }
        return 0;
    }

    public static int findWaterPosition(Type type) {
        int i = 0;
        Type[] array = Type.values();
        while (i < array.length) {
            if (type.equals(array[i])) {
                i++;
            }
        }
        return 0;
    }



    /**
     *
     * @return the display string representation
     */
    @Override
    public String toString() {
        return _name + " " + _location + " " + _water;
    }


    /* *********************************
     * These methods are required by the parcelable interface
     *
     */

    private SourceReport(Parcel in) {
        _name = in.readString();
//        _location = in.read;
//        _water = in.readInt();
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
        dest.writeString(_name);
//        dest.writeString(_major);
//        dest.writeInt(_id);
    }

    public static final Parcelable.Creator<SourceReport> CREATOR
            = new Parcelable.Creator<SourceReport>() {
        public SourceReport createFromParcel(Parcel in) {
            return new SourceReport(in);
        }

        public SourceReport[] newArray(int size) {
            return new SourceReport[size];
        }
    };
}
