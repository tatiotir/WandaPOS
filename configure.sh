#!/bin/sh

#    Wanda POS - Africa's Gift to the World
#    Copyright (c) 2014-2015 IT-Kamer & previous Unicenta POS and Wanda POS works
#    www.erp-university-africa.com
#	 http://sourceforge.net/projects/wandaposdapos
#
#    This file is part of Wanda POS.
#
#    Wanda POS is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    Wanda POS is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with Wanda POS.  If not, see <http://www.gnu.org/licenses/>.

DIRNAME=`dirname $0`
CP=$DIRNAME/wandapos.jar
CP=$CP:$DIRNAME/locales/
CP=$CP:$DIRNAME/lib/substance.jar
java -cp $CP -Djava.util.logging.config.file=$DIRNAME/logging.properties -Dswing.defaultlaf=javax.swing.plaf.metal.MetalLookAndFeel com.openbravo.pos.config.JFrmConfig
