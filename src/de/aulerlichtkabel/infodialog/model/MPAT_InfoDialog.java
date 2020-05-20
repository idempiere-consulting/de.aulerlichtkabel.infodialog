/******************************************************************************
 * Plug-in InfoDialog for iDempiere ERP & CRM Smart Business Solution *
 * Copyright (C) 2019  Patric Maßing (Hans Auler GmbH)                        *
 *                                                                            *
 * This plug-in is free software; you can redistribute it and/or modify       *
 * it under the terms of the GNU General Public License as published by       *
 * the Free Software Foundation; either version 2 of the License, or          *
 * (at your option) any later version.                                        *
 *                                                                            *
 * This plug-in is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU General Public License along    *
 * with this plug-in; If not, see <http://www.gnu.org/licenses/>.             *
 *****************************************************************************/
 
 /**
  * @author Patric Maßing (Hans Auler GmbH)
  * 2019
 */

package de.aulerlichtkabel.infodialog.model;

import java.sql.ResultSet;
import java.util.Properties;


public class MPAT_InfoDialog extends X_PAT_InfoDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8860782173319678294L;

	public MPAT_InfoDialog(Properties ctx, int PAT_InfoDialog_ID, String trxName) {
		super(ctx, PAT_InfoDialog_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPAT_InfoDialog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	

}
