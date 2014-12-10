package com.cloudera.sa.nexgen.avro;

/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Active_E_RAB_Number_15m extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Active_E_RAB_Number_15m\",\"fields\":[{\"name\":\"MMEName\",\"type\":[\"string\",\"null\"]},{\"name\":\"SWRelease\",\"type\":[\"string\",\"null\"]},{\"name\":\"ENodeBId\",\"type\":[\"int\",\"null\"]},{\"name\":\"ENodeBName\",\"type\":[\"string\",\"null\"]},{\"name\":\"RecordTimestamp\",\"type\":[\"string\",\"null\"]},{\"name\":\"CNum\",\"type\":[\"string\",\"null\"]},{\"name\":\"QCI\",\"type\":[\"string\",\"null\"]},{\"name\":\"UsageNbrAvg\",\"type\":[\"float\",\"null\"]},{\"name\":\"UsageNbrMax\",\"type\":[\"float\",\"null\"]},{\"name\":\"UsageNbrTot\",\"type\":[\"float\",\"null\"]},{\"name\":\"UsageNbrCnt\",\"type\":[\"int\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence MMEName;
  @Deprecated public java.lang.CharSequence SWRelease;
  @Deprecated public java.lang.Integer ENodeBId;
  @Deprecated public java.lang.CharSequence ENodeBName;
  @Deprecated public java.lang.CharSequence RecordTimestamp;
  @Deprecated public java.lang.CharSequence CNum;
  @Deprecated public java.lang.CharSequence QCI;
  @Deprecated public java.lang.Float UsageNbrAvg;
  @Deprecated public java.lang.Float UsageNbrMax;
  @Deprecated public java.lang.Float UsageNbrTot;
  @Deprecated public java.lang.Integer UsageNbrCnt;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Active_E_RAB_Number_15m() {}

  /**
   * All-args constructor.
   */
  public Active_E_RAB_Number_15m(java.lang.CharSequence MMEName, java.lang.CharSequence SWRelease, java.lang.Integer ENodeBId, java.lang.CharSequence ENodeBName, java.lang.CharSequence RecordTimestamp, java.lang.CharSequence CNum, java.lang.CharSequence QCI, java.lang.Float UsageNbrAvg, java.lang.Float UsageNbrMax, java.lang.Float UsageNbrTot, java.lang.Integer UsageNbrCnt) {
    this.MMEName = MMEName;
    this.SWRelease = SWRelease;
    this.ENodeBId = ENodeBId;
    this.ENodeBName = ENodeBName;
    this.RecordTimestamp = RecordTimestamp;
    this.CNum = CNum;
    this.QCI = QCI;
    this.UsageNbrAvg = UsageNbrAvg;
    this.UsageNbrMax = UsageNbrMax;
    this.UsageNbrTot = UsageNbrTot;
    this.UsageNbrCnt = UsageNbrCnt;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return MMEName;
    case 1: return SWRelease;
    case 2: return ENodeBId;
    case 3: return ENodeBName;
    case 4: return RecordTimestamp;
    case 5: return CNum;
    case 6: return QCI;
    case 7: return UsageNbrAvg;
    case 8: return UsageNbrMax;
    case 9: return UsageNbrTot;
    case 10: return UsageNbrCnt;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: MMEName = (java.lang.CharSequence)value$; break;
    case 1: SWRelease = (java.lang.CharSequence)value$; break;
    case 2: ENodeBId = (java.lang.Integer)value$; break;
    case 3: ENodeBName = (java.lang.CharSequence)value$; break;
    case 4: RecordTimestamp = (java.lang.CharSequence)value$; break;
    case 5: CNum = (java.lang.CharSequence)value$; break;
    case 6: QCI = (java.lang.CharSequence)value$; break;
    case 7: UsageNbrAvg = (java.lang.Float)value$; break;
    case 8: UsageNbrMax = (java.lang.Float)value$; break;
    case 9: UsageNbrTot = (java.lang.Float)value$; break;
    case 10: UsageNbrCnt = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'MMEName' field.
   */
  public java.lang.CharSequence getMMEName() {
    return MMEName;
  }

  /**
   * Sets the value of the 'MMEName' field.
   * @param value the value to set.
   */
  public void setMMEName(java.lang.CharSequence value) {
    this.MMEName = value;
  }

  /**
   * Gets the value of the 'SWRelease' field.
   */
  public java.lang.CharSequence getSWRelease() {
    return SWRelease;
  }

  /**
   * Sets the value of the 'SWRelease' field.
   * @param value the value to set.
   */
  public void setSWRelease(java.lang.CharSequence value) {
    this.SWRelease = value;
  }

  /**
   * Gets the value of the 'ENodeBId' field.
   */
  public java.lang.Integer getENodeBId() {
    return ENodeBId;
  }

  /**
   * Sets the value of the 'ENodeBId' field.
   * @param value the value to set.
   */
  public void setENodeBId(java.lang.Integer value) {
    this.ENodeBId = value;
  }

  /**
   * Gets the value of the 'ENodeBName' field.
   */
  public java.lang.CharSequence getENodeBName() {
    return ENodeBName;
  }

  /**
   * Sets the value of the 'ENodeBName' field.
   * @param value the value to set.
   */
  public void setENodeBName(java.lang.CharSequence value) {
    this.ENodeBName = value;
  }

  /**
   * Gets the value of the 'RecordTimestamp' field.
   */
  public java.lang.CharSequence getRecordTimestamp() {
    return RecordTimestamp;
  }

  /**
   * Sets the value of the 'RecordTimestamp' field.
   * @param value the value to set.
   */
  public void setRecordTimestamp(java.lang.CharSequence value) {
    this.RecordTimestamp = value;
  }

  /**
   * Gets the value of the 'CNum' field.
   */
  public java.lang.CharSequence getCNum() {
    return CNum;
  }

  /**
   * Sets the value of the 'CNum' field.
   * @param value the value to set.
   */
  public void setCNum(java.lang.CharSequence value) {
    this.CNum = value;
  }

  /**
   * Gets the value of the 'QCI' field.
   */
  public java.lang.CharSequence getQCI() {
    return QCI;
  }

  /**
   * Sets the value of the 'QCI' field.
   * @param value the value to set.
   */
  public void setQCI(java.lang.CharSequence value) {
    this.QCI = value;
  }

  /**
   * Gets the value of the 'UsageNbrAvg' field.
   */
  public java.lang.Float getUsageNbrAvg() {
    return UsageNbrAvg;
  }

  /**
   * Sets the value of the 'UsageNbrAvg' field.
   * @param value the value to set.
   */
  public void setUsageNbrAvg(java.lang.Float value) {
    this.UsageNbrAvg = value;
  }

  /**
   * Gets the value of the 'UsageNbrMax' field.
   */
  public java.lang.Float getUsageNbrMax() {
    return UsageNbrMax;
  }

  /**
   * Sets the value of the 'UsageNbrMax' field.
   * @param value the value to set.
   */
  public void setUsageNbrMax(java.lang.Float value) {
    this.UsageNbrMax = value;
  }

  /**
   * Gets the value of the 'UsageNbrTot' field.
   */
  public java.lang.Float getUsageNbrTot() {
    return UsageNbrTot;
  }

  /**
   * Sets the value of the 'UsageNbrTot' field.
   * @param value the value to set.
   */
  public void setUsageNbrTot(java.lang.Float value) {
    this.UsageNbrTot = value;
  }

  /**
   * Gets the value of the 'UsageNbrCnt' field.
   */
  public java.lang.Integer getUsageNbrCnt() {
    return UsageNbrCnt;
  }

  /**
   * Sets the value of the 'UsageNbrCnt' field.
   * @param value the value to set.
   */
  public void setUsageNbrCnt(java.lang.Integer value) {
    this.UsageNbrCnt = value;
  }

  /** Creates a new Active_E_RAB_Number_15m RecordBuilder */
  public static Active_E_RAB_Number_15m.Builder newBuilder() {
    return new Active_E_RAB_Number_15m.Builder();
  }
  
  /** Creates a new Active_E_RAB_Number_15m RecordBuilder by copying an existing Builder */
  public static Active_E_RAB_Number_15m.Builder newBuilder(Active_E_RAB_Number_15m.Builder other) {
    return new Active_E_RAB_Number_15m.Builder(other);
  }
  
  /** Creates a new Active_E_RAB_Number_15m RecordBuilder by copying an existing Active_E_RAB_Number_15m instance */
  public static Active_E_RAB_Number_15m.Builder newBuilder(Active_E_RAB_Number_15m other) {
    return new Active_E_RAB_Number_15m.Builder(other);
  }
  
  /**
   * RecordBuilder for Active_E_RAB_Number_15m instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Active_E_RAB_Number_15m>
    implements org.apache.avro.data.RecordBuilder<Active_E_RAB_Number_15m> {

    private java.lang.CharSequence MMEName;
    private java.lang.CharSequence SWRelease;
    private java.lang.Integer ENodeBId;
    private java.lang.CharSequence ENodeBName;
    private java.lang.CharSequence RecordTimestamp;
    private java.lang.CharSequence CNum;
    private java.lang.CharSequence QCI;
    private java.lang.Float UsageNbrAvg;
    private java.lang.Float UsageNbrMax;
    private java.lang.Float UsageNbrTot;
    private java.lang.Integer UsageNbrCnt;

    /** Creates a new Builder */
    private Builder() {
      super(Active_E_RAB_Number_15m.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(Active_E_RAB_Number_15m.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.MMEName)) {
        this.MMEName = data().deepCopy(fields()[0].schema(), other.MMEName);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.SWRelease)) {
        this.SWRelease = data().deepCopy(fields()[1].schema(), other.SWRelease);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ENodeBId)) {
        this.ENodeBId = data().deepCopy(fields()[2].schema(), other.ENodeBId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.ENodeBName)) {
        this.ENodeBName = data().deepCopy(fields()[3].schema(), other.ENodeBName);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.RecordTimestamp)) {
        this.RecordTimestamp = data().deepCopy(fields()[4].schema(), other.RecordTimestamp);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.CNum)) {
        this.CNum = data().deepCopy(fields()[5].schema(), other.CNum);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.QCI)) {
        this.QCI = data().deepCopy(fields()[6].schema(), other.QCI);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.UsageNbrAvg)) {
        this.UsageNbrAvg = data().deepCopy(fields()[7].schema(), other.UsageNbrAvg);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.UsageNbrMax)) {
        this.UsageNbrMax = data().deepCopy(fields()[8].schema(), other.UsageNbrMax);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.UsageNbrTot)) {
        this.UsageNbrTot = data().deepCopy(fields()[9].schema(), other.UsageNbrTot);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.UsageNbrCnt)) {
        this.UsageNbrCnt = data().deepCopy(fields()[10].schema(), other.UsageNbrCnt);
        fieldSetFlags()[10] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Active_E_RAB_Number_15m instance */
    private Builder(Active_E_RAB_Number_15m other) {
            super(Active_E_RAB_Number_15m.SCHEMA$);
      if (isValidValue(fields()[0], other.MMEName)) {
        this.MMEName = data().deepCopy(fields()[0].schema(), other.MMEName);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.SWRelease)) {
        this.SWRelease = data().deepCopy(fields()[1].schema(), other.SWRelease);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ENodeBId)) {
        this.ENodeBId = data().deepCopy(fields()[2].schema(), other.ENodeBId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.ENodeBName)) {
        this.ENodeBName = data().deepCopy(fields()[3].schema(), other.ENodeBName);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.RecordTimestamp)) {
        this.RecordTimestamp = data().deepCopy(fields()[4].schema(), other.RecordTimestamp);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.CNum)) {
        this.CNum = data().deepCopy(fields()[5].schema(), other.CNum);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.QCI)) {
        this.QCI = data().deepCopy(fields()[6].schema(), other.QCI);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.UsageNbrAvg)) {
        this.UsageNbrAvg = data().deepCopy(fields()[7].schema(), other.UsageNbrAvg);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.UsageNbrMax)) {
        this.UsageNbrMax = data().deepCopy(fields()[8].schema(), other.UsageNbrMax);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.UsageNbrTot)) {
        this.UsageNbrTot = data().deepCopy(fields()[9].schema(), other.UsageNbrTot);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.UsageNbrCnt)) {
        this.UsageNbrCnt = data().deepCopy(fields()[10].schema(), other.UsageNbrCnt);
        fieldSetFlags()[10] = true;
      }
    }

    /** Gets the value of the 'MMEName' field */
    public java.lang.CharSequence getMMEName() {
      return MMEName;
    }
    
    /** Sets the value of the 'MMEName' field */
    public Active_E_RAB_Number_15m.Builder setMMEName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.MMEName = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'MMEName' field has been set */
    public boolean hasMMEName() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'MMEName' field */
    public Active_E_RAB_Number_15m.Builder clearMMEName() {
      MMEName = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'SWRelease' field */
    public java.lang.CharSequence getSWRelease() {
      return SWRelease;
    }
    
    /** Sets the value of the 'SWRelease' field */
    public Active_E_RAB_Number_15m.Builder setSWRelease(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.SWRelease = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'SWRelease' field has been set */
    public boolean hasSWRelease() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'SWRelease' field */
    public Active_E_RAB_Number_15m.Builder clearSWRelease() {
      SWRelease = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'ENodeBId' field */
    public java.lang.Integer getENodeBId() {
      return ENodeBId;
    }
    
    /** Sets the value of the 'ENodeBId' field */
    public Active_E_RAB_Number_15m.Builder setENodeBId(java.lang.Integer value) {
      validate(fields()[2], value);
      this.ENodeBId = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'ENodeBId' field has been set */
    public boolean hasENodeBId() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'ENodeBId' field */
    public Active_E_RAB_Number_15m.Builder clearENodeBId() {
      ENodeBId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'ENodeBName' field */
    public java.lang.CharSequence getENodeBName() {
      return ENodeBName;
    }
    
    /** Sets the value of the 'ENodeBName' field */
    public Active_E_RAB_Number_15m.Builder setENodeBName(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.ENodeBName = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'ENodeBName' field has been set */
    public boolean hasENodeBName() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'ENodeBName' field */
    public Active_E_RAB_Number_15m.Builder clearENodeBName() {
      ENodeBName = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'RecordTimestamp' field */
    public java.lang.CharSequence getRecordTimestamp() {
      return RecordTimestamp;
    }
    
    /** Sets the value of the 'RecordTimestamp' field */
    public Active_E_RAB_Number_15m.Builder setRecordTimestamp(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.RecordTimestamp = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'RecordTimestamp' field has been set */
    public boolean hasRecordTimestamp() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'RecordTimestamp' field */
    public Active_E_RAB_Number_15m.Builder clearRecordTimestamp() {
      RecordTimestamp = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'CNum' field */
    public java.lang.CharSequence getCNum() {
      return CNum;
    }
    
    /** Sets the value of the 'CNum' field */
    public Active_E_RAB_Number_15m.Builder setCNum(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.CNum = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'CNum' field has been set */
    public boolean hasCNum() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'CNum' field */
    public Active_E_RAB_Number_15m.Builder clearCNum() {
      CNum = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'QCI' field */
    public java.lang.CharSequence getQCI() {
      return QCI;
    }
    
    /** Sets the value of the 'QCI' field */
    public Active_E_RAB_Number_15m.Builder setQCI(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.QCI = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'QCI' field has been set */
    public boolean hasQCI() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'QCI' field */
    public Active_E_RAB_Number_15m.Builder clearQCI() {
      QCI = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'UsageNbrAvg' field */
    public java.lang.Float getUsageNbrAvg() {
      return UsageNbrAvg;
    }
    
    /** Sets the value of the 'UsageNbrAvg' field */
    public Active_E_RAB_Number_15m.Builder setUsageNbrAvg(java.lang.Float value) {
      validate(fields()[7], value);
      this.UsageNbrAvg = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'UsageNbrAvg' field has been set */
    public boolean hasUsageNbrAvg() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'UsageNbrAvg' field */
    public Active_E_RAB_Number_15m.Builder clearUsageNbrAvg() {
      UsageNbrAvg = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'UsageNbrMax' field */
    public java.lang.Float getUsageNbrMax() {
      return UsageNbrMax;
    }
    
    /** Sets the value of the 'UsageNbrMax' field */
    public Active_E_RAB_Number_15m.Builder setUsageNbrMax(java.lang.Float value) {
      validate(fields()[8], value);
      this.UsageNbrMax = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'UsageNbrMax' field has been set */
    public boolean hasUsageNbrMax() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'UsageNbrMax' field */
    public Active_E_RAB_Number_15m.Builder clearUsageNbrMax() {
      UsageNbrMax = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /** Gets the value of the 'UsageNbrTot' field */
    public java.lang.Float getUsageNbrTot() {
      return UsageNbrTot;
    }
    
    /** Sets the value of the 'UsageNbrTot' field */
    public Active_E_RAB_Number_15m.Builder setUsageNbrTot(java.lang.Float value) {
      validate(fields()[9], value);
      this.UsageNbrTot = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the 'UsageNbrTot' field has been set */
    public boolean hasUsageNbrTot() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the 'UsageNbrTot' field */
    public Active_E_RAB_Number_15m.Builder clearUsageNbrTot() {
      UsageNbrTot = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /** Gets the value of the 'UsageNbrCnt' field */
    public java.lang.Integer getUsageNbrCnt() {
      return UsageNbrCnt;
    }
    
    /** Sets the value of the 'UsageNbrCnt' field */
    public Active_E_RAB_Number_15m.Builder setUsageNbrCnt(java.lang.Integer value) {
      validate(fields()[10], value);
      this.UsageNbrCnt = value;
      fieldSetFlags()[10] = true;
      return this; 
    }
    
    /** Checks whether the 'UsageNbrCnt' field has been set */
    public boolean hasUsageNbrCnt() {
      return fieldSetFlags()[10];
    }
    
    /** Clears the value of the 'UsageNbrCnt' field */
    public Active_E_RAB_Number_15m.Builder clearUsageNbrCnt() {
      UsageNbrCnt = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    @Override
    public Active_E_RAB_Number_15m build() {
      try {
        Active_E_RAB_Number_15m record = new Active_E_RAB_Number_15m();
        record.MMEName = fieldSetFlags()[0] ? this.MMEName : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.SWRelease = fieldSetFlags()[1] ? this.SWRelease : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.ENodeBId = fieldSetFlags()[2] ? this.ENodeBId : (java.lang.Integer) defaultValue(fields()[2]);
        record.ENodeBName = fieldSetFlags()[3] ? this.ENodeBName : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.RecordTimestamp = fieldSetFlags()[4] ? this.RecordTimestamp : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.CNum = fieldSetFlags()[5] ? this.CNum : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.QCI = fieldSetFlags()[6] ? this.QCI : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.UsageNbrAvg = fieldSetFlags()[7] ? this.UsageNbrAvg : (java.lang.Float) defaultValue(fields()[7]);
        record.UsageNbrMax = fieldSetFlags()[8] ? this.UsageNbrMax : (java.lang.Float) defaultValue(fields()[8]);
        record.UsageNbrTot = fieldSetFlags()[9] ? this.UsageNbrTot : (java.lang.Float) defaultValue(fields()[9]);
        record.UsageNbrCnt = fieldSetFlags()[10] ? this.UsageNbrCnt : (java.lang.Integer) defaultValue(fields()[10]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
