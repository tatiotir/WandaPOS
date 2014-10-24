--    Wanda POS - Africa's Gift to the World
--    Copyright (c) 2014-2015 Wanda POS
--    https://sourceforge.net/projects/wandaposdapos/
--
--    This file is part of Wanda POS.
--
--    Wanda POS is free software: you can redistribute it and/or modify
--    it under the terms of the GNU General Public License as published by
--    the Free Software Foundation, either version 3 of the License, or
--    (at your option) any later version.
--
--    Wanda POS is distributed in the hope that it will be useful,
--    but WITHOUT ANY WARRANTY; without even the implied warranty of
--    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
--    GNU General Public License for more details.
--
--    You should have received a copy of the GNU General Public License
--    along with Wanda POS.  If not, see <http://www.gnu.org/licenses/>.

-- Database create script for MySQL
-- Copyright (c) 2014-2015 Wanda POS
-- v3.70


COLLECTION=roles;{_id : 0, NAME : Administrator role, PERMISSIONS : $FILE{/com/openbravo/pos/templates/Role.Administrator.xml}}
COLLECTION=roles;{_id : 1, NAME : Manager role, PERMISSIONS : $FILE{/com/openbravo/pos/templates/Role.Manager.xml}}
COLLECTION=roles;{_id : 2, NAME : Employee role, PERMISSIONS : $FILE{/com/openbravo/pos/templates/Role.Employee.xml}}
COLLECTION=roles;{_id : 3, NAME : Guest role, PERMISSIONS : $FILE{/com/openbravo/pos/templates/Role.Guest.xml}}

COLLECTION=people;{_id : 0, NAME : Administrator, ROLE : 0, VISIBLE : 1}
COLLECTION=people;{_id : 1, NAME : Manager, ROLE : 1, VISIBLE : 1}
COLLECTION=people;{_id : 2, NAME : Employee, ROLE : 2, VISIBLE : 1}
COLLECTION=people;{_id : 3, NAME : Guest, ROLE : 3, VISIBLE : 1}

COLLECTION=resources;{_id : 0, NAME : Menu.Root, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Menu.Root.txt}}
COLLECTION=resources;{_id : 1, NAME : coin.2, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/coin.2.png}}
COLLECTION=resources;{_id : 2, NAME : coin.1, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/coin.1.png}}
COLLECTION=resources;{_id : 3, NAME : coin.50, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/coin.50.png}}
COLLECTION=resources;{_id : 4, NAME : coin.20, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/coin.20.png}}
COLLECTION=resources;{_id : 5, NAME : coin.10, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/coin.10.png}}
COLLECTION=resources;{_id : 6, NAME : coin.05, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/coin.05.png}}
COLLECTION=resources;{_id : 7, NAME : coin.02, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/coin.02.png}}
COLLECTION=resources;{_id : 8, NAME : coin.01, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/coin.01.png}}
COLLECTION=resources;{_id : 9, NAME : img.cash, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.cash.png}}
COLLECTION=resources;{_id : 10, NAME : img.cashdrawer, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.cashdrawer.png}}
COLLECTION=resources;{_id : 11, NAME : img.discount, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.discount.png}}
COLLECTION=resources;{_id : 12, NAME : img.empty, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.empty.png}}
COLLECTION=resources;{_id : 13, NAME : img.heart, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.heart.png}}
COLLECTION=resources;{_id : 14, NAME : img.no_photo, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.no_photo.png}}
COLLECTION=resources;{_id : 15, NAME : img.kit_print, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.kit_print.png}}
COLLECTION=resources;{_id : 16, NAME : img.refundit, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.refundit.png}}
COLLECTION=resources;{_id : 17, NAME : img.run_script, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.run_script.png}}
COLLECTION=resources;{_id : 18, NAME : img.ticket_print, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.ticket_print.png}}
COLLECTION=resources;{_id : 19, NAME : img.user, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/img.user.png}}
COLLECTION=resources;{_id : 20, NAME : note.50, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/note.50.png}}
COLLECTION=resources;{_id : 21, NAME : note.20, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/note.20.png}}
COLLECTION=resources;{_id : 22, NAME : note.10, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/note.10.png}}
COLLECTION=resources;{_id : 23, NAME : note.5, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/note.5.png}}
COLLECTION=resources;{_id : 24, NAME : payment.cash, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/payment.cash.txt}}
COLLECTION=resources;{_id : 25, NAME : Printer.CloseCash, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.CloseCash.xml}}
COLLECTION=resources;{_id : 26, NAME : Printer.CustomerPaid, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.CustomerPaid.xml}}
COLLECTION=resources;{_id : 27, NAME : Printer.CustomerPaid2, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.CustomerPaid2.xml}}
COLLECTION=resources;{_id : 28, NAME : Printer.FiscalTicket, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.FiscalTicket.xml}}
COLLECTION=resources;{_id : 29, NAME : Printer.Inventory, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.Inventory.xml}}
COLLECTION=resources;{_id : 30, NAME : Printer.OpenDrawer, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.OpenDrawer.xml}}
COLLECTION=resources;{_id : 31, NAME : Printer.PartialCash, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.PartialCash.xml}}
COLLECTION=resources;{_id : 32, NAME : Printer.Product, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.Product.xml}}
COLLECTION=resources;{_id : 33, NAME : Printer.Start, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.Start.xml}}
COLLECTION=resources;{_id : 34, NAME : Printer.Ticket, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.Ticket.xml}}
COLLECTION=resources;{_id : 35, NAME : Printer.Ticket2, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.Ticket2.xml}}
COLLECTION=resources;{_id : 36, NAME : Printer.TicketClose, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.TicketClose.xml}}
COLLECTION=resources;{_id : 37, NAME : Printer.TicketKitchen, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.TicketKitchen.xml}}
COLLECTION=resources;{_id : 38, NAME : Printer.TicketLine, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.TicketLine.xml}}
COLLECTION=resources;{_id : 39, NAME : Printer.TicketNew, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.TicketLine.xml}}
COLLECTION=resources;{_id : 40, NAME : Printer.TicketPreview, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.TicketPreview.xml}}
COLLECTION=resources;{_id : 41, NAME : Printer.TicketTotal, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Printer.TicketTotal.xml}}
COLLECTION=resources;{_id : 42, NAME : Printer.Ticket.Logo, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/printer.ticket.logo.png}}
COLLECTION=resources;{_id : 43, NAME : script.AddLineNote, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.AddLineNote.txt}}
COLLECTION=resources;{_id : 44, NAME : script.Event.Total, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.Event.Total.txt}}
COLLECTION=resources;{_id : 45, NAME : script.linediscount, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.linediscount.txt}}
COLLECTION=resources;{_id : 46, NAME : script.ReceiptConsolidate, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.ReceiptConsolidate.txt}}
COLLECTION=resources;{_id : 47, NAME : script.Refundit, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.Refundit.txt}}
COLLECTION=resources;{_id : 48, NAME : script.SendOrder, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.SendOrder.txt}}
COLLECTION=resources;{_id : 49, NAME : script.ServiceCharge, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.script.ServiceCharge.txt}}
COLLECTION=resources;{_id : 50, NAME : script.SetPerson, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.SetPerson.txt}}
COLLECTION=resources;{_id : 51, NAME : script.StockCurrentAdd, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.StockCurrentAdd.txt}}
COLLECTION=resources;{_id : 52, NAME : script.StockCurrentSet, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.StockCurrentSet.txt}}
COLLECTION=resources;{_id : 53, NAME : script.totaldiscount, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/script.totaldiscount.txt}}
COLLECTION=resources;{_id : 54, NAME : Ticket.Buttons, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Ticket.Buttons.xml}}
COLLECTION=resources;{_id : 55, NAME : Ticket.Close, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Ticket.Close.xml}}
COLLECTION=resources;{_id : 56, NAME : Ticket.Discount, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Ticket.Discount.xml}}
COLLECTION=resources;{_id : 57, NAME : Ticket.Line, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Ticket.Line.xml}}
COLLECTION=resources;{_id : 58, NAME : Ticket.TicketLineTaxesIncluded, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Ticket.TicketLineTaxesIncluded.xml}}
COLLECTION=resources;{_id : 59, NAME : Window.Logo, RESTYPE : 1, CONTENT : $FILE{/com/openbravo/pos/templates/window.logo.png}}
COLLECTION=resources;{_id : 60, NAME : Window.Title, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/Window.Title.txt}}
COLLECTION=resources;{_id : 61, NAME : wandapos.properties, RESTYPE : 0, CONTENT : $FILE{/com/openbravo/pos/templates/wandapos.properties.txt}}

COLLECTION=categories;{_id : 000, NAME : Category Standard}

COLLECTION=taxcategories;{_id : 000, NAME : Tax Exempt}
COLLECTION=taxcategories;{_id : 001, NAME : Tax Standard}

COLLECTION=taxes;{_id : 000, NAME : Tax Exempt, CATEGORY : 000, RATE : 0, RATECASCADE : FALSE}
COLLECTION=taxes;{_id : 001, NAME : Tax Standard, CATEGORY : 001, RATE : 0.10, RATECASCADE : FALSE}

COLLECTION=products;{_id : xxx999_999xxx_x9x9x9, REFERENCE : xxx999, CODE : xxx999, NAME : ***, CATEGORY : 000, TAXCAT : 001, ISSERVICE : 1}

COLLECTION=products_cat;{PRODUCT : xxx999_999xxx_x9x9x9}

COLLECTION=locations;{_id : 0, NAME : General}

COLLECTION=ticketsnum;{_id : 1}

COLLECTION=ticketsnum_refund;{_id : 1}

COLLECTION=ticketsnum_payment;{_id : 1}

COLLECTION=floors;{_id : 0, NAME : Restaurant floor, IMAGE : $FILE{/com/openbravo/pos/templates/restaurant_floor.png}}

COLLECTION=places;{_id : 1, NAME : Table 1, X : 80, Y : 70, FLOOR : 0}
COLLECTION=places;{_id : 2, NAME : Table 2, X : 250, Y : 75, FLOOR : 0}
COLLECTION=places;{_id : 3, NAME : Table 3, X : 400, Y : 75, FLOOR : 0}
COLLECTION=places;{_id : 4, NAME : Table 4, X : 80, Y : 200, FLOOR : 0}
COLLECTION=places;{_id : 5, NAME : Table 5, X : 260, Y : 210, FLOOR : 0}
COLLECTION=places;{_id : 6, NAME : Table 6, X : 430, Y : 210, FLOOR : 0}
COLLECTION=places;{_id : 7, NAME : Table 7, X : 80, Y : 330, FLOOR : 0}
COLLECTION=places;{_id : 8, NAME : Table 8, X : 190, Y : 350, FLOOR : 0}
COLLECTION=places;{_id : 9, NAME : Table 9, X : 295, Y : 350, FLOOR : 0}
COLLECTION=places;{_id : 10, NAME : Table 10, X : 430, Y : 345, FLOOR : 0}
COLLECTION=places;{_id : 11, NAME : Table 11, X : 550, Y : 135, FLOOR : 0}
COLLECTION=places;{_id : 12, NAME : Table 12, X : 550, Y : 290, FLOOR : 0}

COLLECTION=shifts;{_id : 0, STARTSHIFT : 2013-12-02 00:00:00.001, ENDSHIFT : 2013-12-02 00:00:00.002, PPLID : 0}

COLLECTION=breaks;{_id : 0, NAME : Lunch Break, VISIBLE : 1}
COLLECTION=breaks;{_id : 1, NAME : Tea Break, VISIBLE : 1}
COLLECTION=breaks;{_id : 2, NAME : Mid Break, VISIBLE : 1}

COLLECTION=shift_breaks;{_id : 0, SHIFTID : 0, BREAKID : 0, STARTTIME : 2013-12-02 00:00:00.003, ENDTIME : 2013-12-02 00:00:00.004}

COLLECTION=pickup_number;{_id : 1}

COLLECTION=applications;{_id : $APP_ID{}, NAME : $APP_NAME{}, VERSION : $APP_VERSION{}}