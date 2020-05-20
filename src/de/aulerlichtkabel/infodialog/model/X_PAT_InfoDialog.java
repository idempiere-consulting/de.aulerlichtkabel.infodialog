/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package de.aulerlichtkabel.infodialog.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for PAT_InfoDialog
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_PAT_InfoDialog extends PO implements I_PAT_InfoDialog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200314L;

    /** Standard Constructor */
    public X_PAT_InfoDialog (Properties ctx, int PAT_InfoDialog_ID, String trxName)
    {
      super (ctx, PAT_InfoDialog_ID, trxName);
      /** if (PAT_InfoDialog_ID == 0)
        {
			setAD_Tab_ID (0);
			setAD_Window_ID (0);
			setPAT_InfoDialog_ID (0);
			setPosition (0);
// 10
        } */
    }

    /** Load Constructor */
    public X_PAT_InfoDialog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PAT_InfoDialog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Tab getAD_Tab() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Tab)MTable.get(getCtx(), org.compiere.model.I_AD_Tab.Table_Name)
			.getPO(getAD_Tab_ID(), get_TrxName());	}

	/** Set Tab.
		@param AD_Tab_ID 
		Tab within a Window
	  */
	public void setAD_Tab_ID (int AD_Tab_ID)
	{
		if (AD_Tab_ID < 1) 
			set_Value (COLUMNNAME_AD_Tab_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Tab_ID, Integer.valueOf(AD_Tab_ID));
	}

	/** Get Tab.
		@return Tab within a Window
	  */
	public int getAD_Tab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Window getAD_Window() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Window)MTable.get(getCtx(), org.compiere.model.I_AD_Window.Table_Name)
			.getPO(getAD_Window_ID(), get_TrxName());	}

	/** Set Window.
		@param AD_Window_ID 
		Data entry or display window
	  */
	public void setAD_Window_ID (int AD_Window_ID)
	{
		if (AD_Window_ID < 1) 
			set_Value (COLUMNNAME_AD_Window_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Window_ID, Integer.valueOf(AD_Window_ID));
	}

	/** Get Window.
		@return Data entry or display window
	  */
	public int getAD_Window_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Window_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Heading only.
		@param IsHeading 
		Field without Column - Only label is displayed
	  */
	public void setIsHeading (boolean IsHeading)
	{
		set_Value (COLUMNNAME_IsHeading, Boolean.valueOf(IsHeading));
	}

	/** Get Heading only.
		@return Field without Column - Only label is displayed
	  */
	public boolean isHeading () 
	{
		Object oo = get_Value(COLUMNNAME_IsHeading);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Label.
		@param Label Label	  */
	public void setLabel (String Label)
	{
		set_Value (COLUMNNAME_Label, Label);
	}

	/** Get Label.
		@return Label	  */
	public String getLabel () 
	{
		return (String)get_Value(COLUMNNAME_Label);
	}

	/** Set PAT_InfoDialog.
		@param PAT_InfoDialog_ID PAT_InfoDialog	  */
	public void setPAT_InfoDialog_ID (int PAT_InfoDialog_ID)
	{
		if (PAT_InfoDialog_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PAT_InfoDialog_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PAT_InfoDialog_ID, Integer.valueOf(PAT_InfoDialog_ID));
	}

	/** Get PAT_InfoDialog.
		@return PAT_InfoDialog	  */
	public int getPAT_InfoDialog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PAT_InfoDialog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getPAT_InfoDialog_ID()));
    }

	/** Set PAT_InfoDialog_UU.
		@param PAT_InfoDialog_UU PAT_InfoDialog_UU	  */
	public void setPAT_InfoDialog_UU (String PAT_InfoDialog_UU)
	{
		set_Value (COLUMNNAME_PAT_InfoDialog_UU, PAT_InfoDialog_UU);
	}

	/** Get PAT_InfoDialog_UU.
		@return PAT_InfoDialog_UU	  */
	public String getPAT_InfoDialog_UU () 
	{
		return (String)get_Value(COLUMNNAME_PAT_InfoDialog_UU);
	}

	/** Set Position.
		@param Position Position	  */
	public void setPosition (int Position)
	{
		set_Value (COLUMNNAME_Position, Integer.valueOf(Position));
	}

	/** Get Position.
		@return Position	  */
	public int getPosition () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Position);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}


	/** Set SQLStatement.
		@param SQLStatement SQLStatement	  */
	public void setSQLStatement (String SQLStatement)
	{
		set_Value (COLUMNNAME_SQLStatement, SQLStatement);
	}

	/** Get SQLStatement.
		@return SQLStatement	  */
	public String getSQLStatement () 
	{
		return (String)get_Value(COLUMNNAME_SQLStatement);
	}
}