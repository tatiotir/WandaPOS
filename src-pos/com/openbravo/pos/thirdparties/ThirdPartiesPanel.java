//    Wanda POS  - Africa's Gift to the World
//    Copyright (c) 2014-2015 Wanda POS
//    http://www.wandaapos.com
//
//    This file is part of Wanda POS
//
//    Wanda POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   Wanda POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Wanda POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.thirdparties;

import javax.swing.ListCellRenderer;
import com.openbravo.data.gui.ListCellRendererBasic;
import com.openbravo.data.loader.ComparatorCreator;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.panels.*;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.data.loader.Vectorer;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.SaveProvider;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;

/**
 *
 * @author JG uniCenta
 */
public class ThirdPartiesPanel extends JPanelTable {
    
    private TableDefinition tthirdparties;
    private ThirdPartiesView jeditor;
    
    /** Creates a new instance of JPanelPeople */
    public ThirdPartiesPanel() {
    }
    
    /**
     *
     */
    protected void init() {
        DataLogicThirdParties dlThirdParties = (DataLogicThirdParties) app.getBean("com.openbravo.pos.thirdparties.DataLogicThirdParties");        
        tthirdparties = dlThirdParties.getTableThirdParties();        
        jeditor = new ThirdPartiesView(app, dirty);     
    }
    
    /**
     *
     * @return
     */
    public ListProvider getListProvider() {
        return new ListProviderCreator(tthirdparties);
    }
    
    /**
     *
     * @return
     */
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tthirdparties);      
    }
    
    /**
     *
     * @return
     */
    public Vectorer getVectorer() {
        return tthirdparties.getVectorerBasic(new int[]{1, 2, 3, 4});
    }
    
    /**
     *
     * @return
     */
    public ComparatorCreator getComparatorCreator() {
        return tthirdparties.getComparatorCreator(new int[] {1, 2, 3, 4});
    }
    
    /**
     *
     * @return
     */
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tthirdparties.getRenderStringBasic(new int[]{1, 2}));
    }
    
    /**
     *
     * @return
     */
    public EditorRecord getEditor() {
        return jeditor;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return AppLocal.getIntString("Menu.ThirdPartiesManagement");
    }     
}
