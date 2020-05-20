# ReadMe

**Maintainer:** Patric Massing ([Hans Auler GmbH](https://auler.gmbh))  
**Status:** Testing  
**Licence:** GPLv2  
**Author:** Patric Massing ([Hans Auler GmbH](https://auler.gmbh)), Germany  
**Date:** 2019  
**Required environment:** iDempiere 7.1 or Testing, tested with 7.1 and Postgresql Database  
**Description:** A custom window based modal form provides additional self defined information  
**Documentation:** Patric Massing (Hans Auler GmbH)  
<br>
***
This Plug-in is distributed in the hope that it will be useful,  
but WITHOUT ANY WARRANTY; without even the implied warranty of  
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
GNU General Public License for more details.  
***

## Description
Provides a toolbarbutton with startet a free configurable InfoDialog.  
The displayed information can be defined in a configuration window for each Window and Tab.

## Setup

1. Install the plug-in over the osgi-console  

The plugins creates a new menu for configuration and a new toolbarbutton.

## Menu 
PAT_InfoDialog with configuration window PAT_InfoDialog_Config  
Here you can define every information you want to get by starting the dialog in your
current Window, like Sales Order, BPartner or Product..

## Configuration Window

- **Heading only:** - The Dialog has two colums, pos 10 for left column description, pos 20 for right column description.
- **Description:** -
- **Client:** -
- **Organisation:** -
- **Window:** - The Window to get the dialog
- **Tab:** - The Tab to get the dialog
- **Position:** - the order of the row this information is displayed
- **Label:** - label for the information
- **SQLStatement:** - get the information with this statement. Placehoder for current Record_id is #Record_ID#

<br>
Is no configuration for the current window defined and you pressed the toolbar button, the message  
 **"No infos defined"**  
 appears.



