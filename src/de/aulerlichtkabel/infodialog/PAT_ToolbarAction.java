/******************************************************************************
f * Plug-in InfoDialog for iDempiere ERP & CRM Smart Business Solution *
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

package de.aulerlichtkabel.infodialog;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.action.IAction;
import org.adempiere.webui.adwindow.ADWindow;
import org.adempiere.webui.adwindow.ADWindowContent;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridTab;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import de.aulerlichtkabel.infodialog.forms.PAT_InfoDialog;
import de.aulerlichtkabel.infodialog.model.MPAT_InfoDialog;


public class PAT_ToolbarAction extends Window implements IAction {

	private static final long serialVersionUID = 9054414265031990058L;

	private int ad_table_id = 0;
	private int ad_client_id = 0;
	private int ad_org_id = 0;
	private int ad_window_id = 0;
	private int ad_tab_id = 0;
	private int record_id;


	DecimalFormat numberFormat = DisplayType.getNumberFormat(DisplayType.Amount);

	@Override
	public void execute(Object target) {

		Boolean hasConfig = false;
		
		
		ADWindow window = (ADWindow) target;
		ADWindowContent content = window.getADWindowContent();

		GridTab tab = content.getActiveGridTab();
		
		PAT_InfoDialog texteditordlg = new PAT_InfoDialog("", "", true, 200, true);		
		window.getComponent().appendChild(texteditordlg);

		ad_table_id = tab.getAD_Table_ID();
		ad_client_id = (Integer) tab.getValue("AD_Client_ID");
		ad_org_id = (Integer) tab.getValue("AD_Org_ID");
		ad_window_id = window.getAD_Window_ID();
		ad_tab_id = tab.getAD_Tab_ID();
		record_id = tab.getRecord_ID();   //#Record_ID#

		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<String> results = new ArrayList<String>();

		StringBuilder hdescriptionlabel = new StringBuilder();

		StringBuilder hresultlabel = new StringBuilder();

		for (MPAT_InfoDialog pat_InfoDialog : getConfigRecord()) {

			hasConfig = true;

			if (pat_InfoDialog.isHeading()) {

				if (pat_InfoDialog.getPosition() == 10)
					hdescriptionlabel.append(pat_InfoDialog.getLabel());
				if (pat_InfoDialog.getPosition() == 20)
					hresultlabel.append(pat_InfoDialog.getLabel());

				continue;
			}

			StringBuilder label = new StringBuilder(pat_InfoDialog.getLabel());
			labels.add(label.toString());
			StringBuilder sql = new StringBuilder(parseLine(tab, pat_InfoDialog.getSQLStatement()));

			results.add(pat_InfoDialog.getLabel());
			results.add(execQuery(pat_InfoDialog, sql).toString());

		}

		if (hasConfig) {
			texteditordlg
					.setContent(formatInfo(hdescriptionlabel.toString(), hresultlabel.toString(), labels, results));
			LayoutUtils.openOverlappedWindow(window.getComponent(), texteditordlg, "middle_center");
			
		} else {
			texteditordlg.dispose();
			FDialog.info(ad_window_id, this, "No infos defined", Msg.getMsg(Env.getCtx(), ""));
		}

	}

	public MPAT_InfoDialog[] getConfigRecord() {

		StringBuilder whereClause = new StringBuilder();

		whereClause.append(MPAT_InfoDialog.COLUMNNAME_AD_Client_ID + "=? ");
		whereClause.append(" AND ");
		whereClause.append(MPAT_InfoDialog.COLUMNNAME_AD_Org_ID + "=? ");
		whereClause.append(" AND ");
		whereClause.append(MPAT_InfoDialog.COLUMNNAME_AD_Window_ID + "=? ");
		whereClause.append(" AND ");
		whereClause.append(MPAT_InfoDialog.COLUMNNAME_AD_Tab_ID + "=? ");

		List<MPAT_InfoDialog> list = new Query(getCtx(), MPAT_InfoDialog.Table_Name, whereClause.toString(),
				getTrxName()).setParameters(ad_client_id, ad_org_id, ad_window_id, ad_tab_id)
						.setOrderBy(MPAT_InfoDialog.COLUMNNAME_IsHeading + "," + MPAT_InfoDialog.COLUMNNAME_Position)
						.list();

		return list.toArray(new MPAT_InfoDialog[list.size()]);

	}

	private StringBuilder execQuery(MPAT_InfoDialog pat_InfoDialog, StringBuilder sql) {
		

		StringBuilder result = new StringBuilder();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		pstmt = null;
		try {

			pstmt = DB.prepareStatement(sql.toString().replaceAll("#Record_ID#", String.valueOf(record_id)) , null);

			rs = pstmt.executeQuery();
			if (rs.next()) {

				Object o = rs.getObject(1);

				if (o instanceof String) {
					result.append("<p style= text-align:right>" + rs.getString(1)+ "</p>");
				}

				if (o instanceof Integer) {
					Integer num = rs.getInt(1);
					result.append("<p style= text-align:right>" + numberFormat.format(num) + "</p>");
				}

				if (o instanceof BigDecimal) {
					BigDecimal num = rs.getBigDecimal(1);

					if (num.compareTo(Env.ZERO) < 0)
						result.append("<p style= text-align:right> <font style=\" color: red\" >" + numberFormat.format(num) + "</font></p>");
					else
						result.append("<p style= text-align:right> <font style=\" color: black\" >" + numberFormat.format(num) + "</font></p>");
					
				}

				if (o instanceof Timestamp) {
					Timestamp tstamp = rs.getTimestamp(1);
					result.append("<p style= text-align:right> <font style=\" color: blue\" >" +  Env.getLanguage(Env.getCtx()).getDateFormat().format(tstamp) + "</font></p>");
				}

				DB.close(rs, pstmt);

			}

		} catch (SQLException e) {

			// return e.getLocalizedMessage();
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return result;

	}

	private Properties getCtx() {
		return Env.getCtx();
	}

	private String getTrxName() {
		return MTable.get(getCtx(), ad_table_id).get_TrxName();
	}

	private StringBuilder formatInfo(String hdescriptionlabel, String hresultlabel, List<String> labels,
			List<String> results) {

		StringBuilder info = new StringBuilder();

		info.append("<font size=2> <ceenter><table width=\"99%\" cellpadding=\"4\" cellspacing=\"0\">");
		info.append("<tr valign=\"top\">\n");

		info.append("<td width=\"40%\" bgcolor=\"#cccccc\" style=\"background: #cccccc\" "
				+ "style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; "
				+ "border-left: 1px none; border-right: none ; padding-top: 0.1cm; "
				+ "padding-bottom: 0.1cm; padding-left: 0cm; padding-right: 0.1cm\">");
		info.append("<b>" + hdescriptionlabel + "</b>");
		info.append("</td>");

		info.append("<td width=\"40%\" bgcolor=\"#cccccc\" style=\"background: #cccccc\" "
				+ "style=\"border-top: 1px solid #000000; " + "border-bottom: 1px solid #000000; "
				+ "border-left: none; border-right: none; " + "padding-top: 0.1cm; "
				+ "padding-bottom: 0.1cm; padding-left: 0cm; padding-right: 0.1cm\">");
		info.append("<p style= text-align:right><b>" + hresultlabel + "</b></p>");
		info.append("</td>");

		info.append("</tr>");

		for (int x = 0; x < results.size(); x++) {

			info.append("<tr valign=\"top\">");

			info.append(
					"<td width=\"40%\" style=\"border-top: 1px solid #000000; " + "border-bottom: 1px solid #000000; "
							+ "border-left: none ; border-right: none; padding-top: 0.1cm; " + "padding-bottom: 0.1cm; "
							+ "padding-left: 0.1cm; padding-right: 0cm\">");

			// Description
			if (results.get(x) != null)
				info.append("<b>" + results.get(x) + "</b>");
			else
				info.append("");			

			info.append("</td>");

			info.append(
					"<td width=\"50%\" style=\"border-top: 1px solid #000000; " + "border-bottom: 1px solid #000000; "
							+ "border-left: none solid #000000; border-right: none; padding-top: 0.1cm; "
							+ "padding-bottom: 0.1cm; " + "padding-left: 0.1cm; padding-right: 0cm\">");

			// Value
			x++;
			if (results.get(x) != null)
				info.append(results.get(x));
			else
				info.append("");			

			info.append("</td>");

			info.append("</tr>");

		}

		info.append("</table></center> </font>");

		return info;

	}

	public String parseLine(GridTab tab, String sql) {

		if (sql == null || sql.length() == 0)
			return "";

		String token;
		StringBuilder outStr = new StringBuilder();

		int i = sql.indexOf('@');
		while (i != -1) {
			outStr.append(sql.substring(0, i));
			sql = sql.substring(i + 1, sql.length());

			int j = sql.indexOf('@');
			if (j < 0) {
				outStr.append("@");
				break;
			}

			token = sql.substring(0, j);

			StringBuilder value = new StringBuilder();

			MColumn column = new MColumn(Env.getCtx(), tab.getField(token).getAD_Column_ID(), null);

			String types = "Date " + " Date+Time " + " Filename " + " Path " + " Memo " + " Printer Name " + " String "
					+ " Text " + " Text Long " + " Time " + " URL " + " Yes-No";

			if (types.contains(column.getAD_Reference().getName())) {
				value.append("\"");
				value.append(tab.getField(token).getValue());
				value.append("\"");
			} else
				value.append(tab.getField(token).getValue());

			outStr.append(value);

			sql = sql.substring(j + 1, sql.length());
			i = sql.indexOf('@');
		}
		outStr.append(sql);

		return outStr.toString();

	}

}
