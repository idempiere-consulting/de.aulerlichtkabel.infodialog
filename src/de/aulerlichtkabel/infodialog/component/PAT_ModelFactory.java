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

package de.aulerlichtkabel.infodialog.component;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;

import de.aulerlichtkabel.infodialog.model.MPAT_InfoDialog;


public class PAT_ModelFactory implements IModelFactory {

	@Override
	public Class<?> getClass(String tableName) {
		
		
		if (tableName.equals(MPAT_InfoDialog.Table_Name)) {
			return MPAT_InfoDialog.class;
		}

				
		//

		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		
		
		if (tableName.equals(MPAT_InfoDialog.Table_Name)) {
			return new MPAT_InfoDialog(Env.getCtx(), Record_ID, trxName);
		}		
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		
		
		if (tableName.equals(MPAT_InfoDialog.Table_Name)) {
			return new MPAT_InfoDialog(Env.getCtx(), rs, trxName);
		}			
		return null;
	}

}
