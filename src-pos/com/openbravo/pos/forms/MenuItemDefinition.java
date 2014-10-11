//    Wanda POS - Africa's Gift to the World
//    Copyright (c) 2014-2015 IT-Kamer & previous Unicenta POS and Openbravo POS works
//    www.erp-university-africa.com
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

package com.openbravo.pos.forms;

import java.awt.Dimension;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author adrianromero
 */
public class MenuItemDefinition implements MenuElement {
    
    private Action act;
    
    /**
     *
     * @param act
     */
    public MenuItemDefinition(Action act) {
        this.act = act;
    }
    
    /**
     *
     * @param menu
     */
    @Override
    public void addComponent(JPanelMenu menu) {
        
        JButton btn = new JButton(act); 
        
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setRequestFocusEnabled(false);
        btn.setHorizontalAlignment(SwingConstants.LEADING);
        btn.setPreferredSize(new Dimension(150, 40));
        
//        btn.setSize(220, 50);
//        btn.setLocation(p);
//        if (p.x >= 470) {
//            p.x = 20;
//            p.y += 55;
//        } else {
//            p.x += 225;
//        }
//        comp.add(btn);        
        
        menu.addEntry(btn);
    }
}
