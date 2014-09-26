--    WandaPos - Touch Friendly Point Of Sale
--    Copyright (c) 2009-2014 uniCenta
--    http://sourceforge.net/projects/unicentaopos
--
--    This file is part of WandaPos.
--
--    WandaPos is free software: you can redistribute it and/or modify
--    it under the terms of the GNU General Public License as published by
--    the Free Software Foundation, either version 3 of the License, or
--    (at your option) any later version.
--
--    WandaPos is distributed in the hope that it will be useful,
--    but WITHOUT ANY WARRANTY; without even the implied warranty of
--    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
--    GNU General Public License for more details.
--
--    You should have received a copy of the GNU General Public License
--    along with WandaPos.  If not, see <http://www.gnu.org/licenses/>.

-- Database create script for MySQL
-- Copyright (c) 2009-2014 uniCenta
-- v3.70

{"_id" : "0", "NAME" : "Administrator role", "PERMISSIONS" : "$FILE{/com/openbravo/pos/templates/Role.Administrator.xml}"}
{"_id" : "1", "NAME" : "Manager role", "PERMISSIONS" : "$FILE{/com/openbravo/pos/templates/Role.Manager.xml}"}
{"_id" : "2", "NAME" : "Employee role", "PERMISSIONS" : "$FILE{/com/openbravo/pos/templates/Role.Employee.xml}"}
{"_id" : "3", "NAME" : "Guest role", "PERMISSIONS" : "$FILE{/com/openbravo/pos/templates/Role.Guest.xml}"}


{"_id" : "0", "NAME" : "Administrator", "ROLE" : "0", "VISIBLE" : "TRUE"}
{"_id" : "1", "NAME" : "Manager", "ROLE" : "1", "VISIBLE" : "TRUE"}
{"_id" : "2", "NAME" : "Employee", "ROLE" : "2", "VISIBLE" : "TRUE"}
{"_id" : "3", "NAME" : "Guest", "ROLE" : "3", "VISIBLE" : "TRUE"}


{"_id" : "0", "NAME" : "Menu.Root", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Menu.Root.txt}"}
{"_id" : "1", "NAME" : "coin.2", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/coin.2.png}"}
{"_id" : "2", "NAME" : "coin.1", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/coin.1.png}"}
{"_id" : "3", "NAME" : "coin.50", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/coin.50.png}"}
{"_id" : "4", "NAME" : "coin.20", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/coin.20.png}"}
{"_id" : "5", "NAME" : "coin.10", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/coin.10.png}"}
{"_id" : "6", "NAME" : "coin.05", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/coin.05.png}"}
{"_id" : "7", "NAME" : "coin.02", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/coin.02.png}"}
{"_id" : "8", "NAME" : "coin.01", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/coin.01.png}"}
{"_id" : "9", "NAME" : "img.cash", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.cash.png}"}
{"_id" : "10", "NAME" : "img.cashdrawer", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.cashdrawer.png}"}
{"_id" : "11", "NAME" : "img.discount", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.discount.png}"}
{"_id" : "12", "NAME" : "img.empty", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.empty.png}"}
{"_id" : "13", "NAME" : "img.heart", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.heart.png}"}
{"_id" : "14", "NAME" : "img.no_photo", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.no_photo.png}"}
{"_id" : "15", "NAME" : "img.kit_print", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.kit_print.png}"}
{"_id" : "16", "NAME" : "img.refundit", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.refundit.png}"}
{"_id" : "17", "NAME" : "img.run_script", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.run_script.png}"}
{"_id" : "18", "NAME" : "img.ticket_print", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.ticket_print.png}"}
{"_id" : "19", "NAME" : "img.user", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/img.user.png}"}
{"_id" : "20", "NAME" : "note.50", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/note.50.png}"}
{"_id" : "21", "NAME" : "note.20", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/note.20.png}"}
{"_id" : "22", "NAME" : "note.10", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/note.10.png}"}
{"_id" : "23", "NAME" : "note.5", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/note.5.png}"}
{"_id" : "24", "NAME" : "payment.cash", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/payment.cash.txt}"}
{"_id" : "25", "NAME" : "Printer.CloseCash", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.CloseCash.xml}"}
{"_id" : "26", "NAME" : "Printer.CustomerPaid", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.CustomerPaid.xml}"}
{"_id" : "27", "NAME" : "Printer.CustomerPaid2", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.CustomerPaid2.xml}"}
{"_id" : "28", "NAME" : "Printer.FiscalTicket", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.FiscalTicket.xml}"}
{"_id" : "29", "NAME" : "Printer.Inventory", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.Inventory.xml}"}
{"_id" : "30", "NAME" : "Printer.OpenDrawer", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.OpenDrawer.xml}"}
{"_id" : "31", "NAME" : "Printer.PartialCash", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.PartialCash.xml}"}
{"_id" : "32", "NAME" : "Printer.Product", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.Product.xml}"}
{"_id" : "33", "NAME" : "Printer.Start", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.Start.xml}"}
{"_id" : "34", "NAME" : "Printer.Ticket", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.Ticket.xml}"}
{"_id" : "35", "NAME" : "Printer.Ticket2", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.Ticket2.xml}"}
{"_id" : "36", "NAME" : "Printer.TicketClose", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.TicketClose.xml}"}
{"_id" : "37", "NAME" : "Printer.TicketKitchen", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.TicketKitchen.xml}"}
{"_id" : "38", "NAME" : "Printer.TicketLine", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.TicketLine.xml}"}
{"_id" : "39", "NAME" : "Printer.TicketNew", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.TicketLine.xml}"}
{"_id" : "40", "NAME" : "Printer.TicketPreview", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.TicketPreview.xml}"}
{"_id" : "41", "NAME" : "Printer.TicketTotal", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Printer.TicketTotal.xml}"}
{"_id" : "42", "NAME" : "Printer.Ticket.Logo", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/printer.ticket.logo.png}"}
{"_id" : "43", "NAME" : "script.AddLineNote", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.AddLineNote.txt}"}
{"_id" : "44", "NAME" : "script.Event.Total", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.Event.Total.txt}"}
{"_id" : "45", "NAME" : "script.linediscount", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.linediscount.txt}"}
{"_id" : "46", "NAME" : "script.ReceiptConsolidate", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.ReceiptConsolidate.txt}"}
{"_id" : "47", "NAME" : "script.Refundit", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.Refundit.txt}"}
{"_id" : "48", "NAME" : "script.SendOrder", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.SendOrder.txt}"}
{"_id" : "49", "NAME" : "script.ServiceCharge", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.script.ServiceCharge.txt}"}
{"_id" : "50", "NAME" : "script.SetPerson", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.SetPerson.txt}"}
{"_id" : "51", "NAME" : "script.StockCurrentAdd", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.StockCurrentAdd.txt}"}
{"_id" : "52", "NAME" : "script.StockCurrentSet", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.StockCurrentSet.txt}"}
{"_id" : "53", "NAME" : "script.totaldiscount", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/script.totaldiscount.txt}"}
{"_id" : "54", "NAME" : "Ticket.Buttons", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Ticket.Buttons.xml}"}
{"_id" : "55", "NAME" : "Ticket.Close", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Ticket.Close.xml}"}
{"_id" : "56", "NAME" : "Ticket.Discount", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Ticket.Discount.xml}"}
{"_id" : "57", "NAME" : "Ticket.Line", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Ticket.Line.xml}"}
{"_id" : "58", "NAME" : "Ticket.TicketLineTaxesIncluded", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Ticket.TicketLineTaxesIncluded.xml}"}
{"_id" : "59", "NAME" : "Window.Logo", "RESTYPE" : "1", "CONTENT" : "$FILE{/com/openbravo/pos/templates/window.logo.png}"}
{"_id" : "60", "NAME" : "Window.Title", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/Window.Title.txt}"}
{"_id" : "61", "NAME" : "openbravo.properties", "RESTYPE" : "0", "CONTENT" : "$FILE{/com/openbravo/pos/templates/openbravo.properties.txt}"}


{"_id" : "000", "NAME" : "Category Standard"}


{"_id" : "000", "NAME" : "Tax Exempt"}
{"_id" : "001", "NAME" : "Tax Standard"}


{"_id" : "000", "NAME" : "Tax Exempt", "CATEGORY" : "000", "RATE" : "0", "RATECASCADE" : "FALSE"}
{"_id" : "001", "NAME" : "Tax Standard", "CATEGORY" : "001", "RATE" : "0.10", "RATECASCADE" : "FALSE"}


{"_id" : "xxx999_999xxx_x9x9x9", "REFERENCE" : "xxx999", "CODE" : "xxx999", "NAME" : "***", "CATEGORY" : "000", "TAXCAT" : "001", "ISSERVICE" : "1"}


{"PRODUCT" : "xxx999_999xxx_x9x9x9"}


{"_id" : "0", "NAME" : "General"}


{"_id" : "1"}


{"_id" : "1"}


{"DEFAULT" : ""}


{"_id" : "0", "NAME" : "Restaurant floor", "IMAGE" : "$FILE{/com/openbravo/pos/templates/restaurant_floor.png}"}
{"_id" : "1", "NAME" : "Table 1", "X" : "80", "Y" : "70", "FLOOR" : "0"}
{"_id" : "2", "NAME" : "Table 2", "X" : "250", "Y" : "75", "FLOOR" : "0"}
{"_id" : "3", "NAME" : "Table 3", "X" : "400", "Y" : "75", "FLOOR" : "0"}
{"_id" : "4", "NAME" : "Table 4", "X" : "80", "Y" : "200", "FLOOR" : "0"}
{"_id" : "5", "NAME" : "Table 5", "X" : "260", "Y" : "210", "FLOOR" : "0"}
{"_id" : "6", "NAME" : "Table 6", "X" : "430", "Y" : "210", "FLOOR" : "0"}
{"_id" : "7", "NAME" : "Table 7", "X" : "80", "Y" : "330", "FLOOR" : "0"}
{"_id" : "8", "NAME" : "Table 8", "X" : "190", "Y" : "350", "FLOOR" : "0"}
{"_id" : "9", "NAME" : "Table 9", "X" : "295", "Y" : "350", "FLOOR" : "0"}
{"_id" : "10", "NAME" : "Table 10", "X" : "430", "Y" : "345", "FLOOR" : "0"}
{"_id" : "11", "NAME" : "Table 11", "X" : "550", "Y" : "135", "FLOOR" : "0"}
{"_id" : "12", "NAME" : "Table 12", "X" : "550", "Y" : "290", "FLOOR" : "0"}


{"_id" : "0", "STARTSHIFT" : "2013-12-02 00:00:00.001", "ENDSHIFT" : "2013-12-02 00:00:00.002", "PPLID" : "0"}


{"_id" : "0", "NAME" : "Lunch Break", "VISIBLE" : "TRUE"}
{"_id" : "1", "NAME" : "Tea Break", "VISIBLE" : "TRUE"}
{"_id" : "2", "NAME" : "Mid Break", "VISIBLE" : "TRUE"}


{"_id" : "0", "SHIFTID" : "0", "BREAKID" : "0", "STARTTIME" : "2013-12-02 00:00:00.003", "ENDTIME" : "2013-12-02 00:00:00.004"}


{"_id" : "1"}


{"_id" : "$APP_ID{}", "NAME" : "$APP_NAME{}", "VERSION" : "$APP_VERSION{}"}