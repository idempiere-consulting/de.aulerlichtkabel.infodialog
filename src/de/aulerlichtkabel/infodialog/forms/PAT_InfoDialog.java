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

package de.aulerlichtkabel.infodialog.forms;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;

import org.adempiere.webui.component.Window;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;



public class PAT_InfoDialog extends Window implements EventListener<Event> {

	private static final long serialVersionUID = -4462007638873561294L;

	Tabpanels tabPanels = new Tabpanels();
	private Tabpanel tabpanelResult = new Tabpanel();

	private Tabbox tabBox = new Tabbox();
	private Tabs tabs = new Tabs();

	private Tab htmlTab = new Tab();

	private StringBuilder htmlcontent = new StringBuilder();

	private Html html = new Html();

	Borderlayout layout = new Borderlayout();

	public PAT_InfoDialog(String title, String text, boolean editable, int maxSize, boolean IsHtml) {

		super();

		setTitle(title);
		init();

	}

	public PAT_InfoDialog(String title, String text, boolean editable, int maxSize) {
		this(title, text, editable, maxSize, false);
	}

	private void init() {
		
		setSclass("popup-dialog");
		setBorder("normal");
		setStyle("position: absolute;");
		setMode("embedded");
		setShadow(true);
		setAttribute(Window.MODE_KEY,  Window.MODE_MODAL );
		
		if (ThemeManager.isUseCSSForWindowSize()) {
			ZKUpdateUtil.setWindowHeightX(this, 400);
			ZKUpdateUtil.setWindowWidthX(this, 450);
		}

		createTabs();

		Div footer = new Div();
		footer.setSclass("dialog-footer");
		ConfirmPanel confirmPanel = new ConfirmPanel(false);
		footer.appendChild(confirmPanel);
		confirmPanel.addActionListener(this);
		appendChild(footer);

	}

	private void createTabs() {

		htmlTab.setLabel(Msg.translate(Env.getCtx(), "Information").replaceAll("[&]", ""));

		tabs.appendChild(htmlTab);

		tabPanels.appendChild(tabpanelResult);
		tabPanels.setVflex("1");

		htmlTab = new Tab("Info");
		tabpanelResult.appendChild(html);

		tabBox.appendChild(tabs);
		tabBox.appendChild(tabPanels);
		tabBox.setVflex("1");

		Center center = new Center();
		center.setAutoscroll(true);
		center.setHflex("1");
		center.setVflex("1");
		center.setParent(layout);

		tabBox.setParent(center);
		appendChild(tabBox);
	}

	public void setContent(StringBuilder content) {

		htmlcontent.append(content);
		html.setContent(htmlcontent.toString());

	}

	public StringBuilder getContent() {
		return htmlcontent;
	}

	public void onEvent(Event event) throws Exception {

		if (event.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			detach();
		}
			
	}

}
