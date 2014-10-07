--    WandaPos - Touch Friendly Point Of Sale
--    Copyright (C) 2009-2014 uniCenta
--    http://www.unicenta.net
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

-- Database upgrade script for PostgreSQL
-- v3.02 - v3.50

CREATE TABLE CSVIMPORT (
  ID VARCHAR NOT NULL,
  ROWNUMBER VARCHAR,
  CSVERROR VARCHAR,
  REFERENCE VARCHAR,
  CODE VARCHAR,
  NAME VARCHAR,
  PRICEBUY DOUBLE PRECISION,
  PRICESELL DOUBLE PRECISION,
  PREVIOUSBUY DOUBLE PRECISION,
  PREVIOUSSELL DOUBLE PRECISION,
  PRIMARY KEY (ID)
);

CREATE TABLE DRAWEROPENED (
    OPENDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    NAME VARCHAR(255),
    TICKETID VARCHAR(255)
);

CREATE TABLE MOORERS (
  VESSELNAME VARCHAR,
  SIZE INTEGER,
  DAYS INTEGER,
  POWER BOOLEAN NOT NULL DEFAULT FALSE
  );

CREATE SEQUENCE PICKUP_NUMBER START WITH 1;

--
-- UPDATE existing tables
--
UPDATE ROLES SET PERMISSIONS = $FILE{/com/openbravo/pos/templates/Role.Administrator.xml} WHERE ID = '0';

UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Menu.Root.txt} WHERE ID = '0';
UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.CloseCash.xml} WHERE ID = '25';
UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.CustomerPaid.XML} WHERE ID = '26';
UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.CustomerPaid2.xml} WHERE ID = '27';
UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.PartialCash.xml} WHERE ID = '31';
UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.Ticket.xml} WHERE ID = '33';
UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.Ticket2.xml} WHERE ID = '34';
UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Printer.TicketPreview.xml} WHERE ID = '37';

-- ADD NEW RESOURCES
INSERT INTO RESOURCES(ID, NAME, RESTYPE, CONTENT) VALUES('56', 'Printer.Product', 0, $FILE{/com/openbravo/pos/templates/Printer.Product.xml});
INSERT INTO RESOURCES(ID, NAME, RESTYPE, CONTENT) VALUES('57', 'Printer.TicketClose', 0, $FILE{/com/openbravo/pos/templates/Printer.TicketClose.xml});
INSERT INTO RESOURCES(ID, NAME, RESTYPE, CONTENT) VALUES('58', 'Printer.TicketNew', 0, $FILE{/com/openbravo/pos/templates/Printer.TicketLine.xml});
INSERT INTO RESOURCES(ID, NAME, RESTYPE, CONTENT) VALUES('59', 'script.AddLineNote', 0, $FILE{/com/openbravo/pos/templates/script.AddLineNote.txt});
INSERT INTO RESOURCES(ID, NAME, RESTYPE, CONTENT) VALUES('60', 'script.ServiceCharge', 0, $FILE{/com/openbravo/pos/templates/script.script.ServiceCharge.txt});

--
-- ALTER existing tables
--
ALTER TABLE CATEGORIES ADD COLUMN TEXTTIP VARCHAR DEFAULT NULL;
ALTER TABLE CATEGORIES ADD COLUMN CATSHOWNAME BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE CLOSEDCASH ADD COLUMN NOSALES SMALLINT DEFAULT 0 NOT NULL;

ALTER TABLE CUSTOMERS ADD COLUMN IMAGE BYTEA;

ALTER TABLE PAYMENTS ADD COLUMN TENDERED DOUBLE PRECISION DEFAULT 0 NOT NULL;
ALTER TABLE PAYMENTS ADD COLUMN CARDNAME VARCHAR;
ALTER TABLE PAYMENTS ADD COLUMN NOTES VARCHAR;
UPDATE PAYMENTS SET TENDERED = TOTAL WHERE TENDERED = 0;

ALTER TABLE PLACES ADD COLUMN CUSTOMER VARCHAR;
ALTER TABLE PLACES ADD COLUMN WAITER VARCHAR;
ALTER TABLE PLACES ADD COLUMN TICKETID VARCHAR;
ALTER TABLE PLACES ADD COLUMN TABLEMOVED BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE PRODUCTS ADD COLUMN ISVPRICE BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE PRODUCTS ADD COLUMN ISVERPATRIB BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE PRODUCTS ADD COLUMN TEXTTIP VARCHAR DEFAULT NULL;
ALTER TABLE PRODUCTS ADD COLUMN WARRANTY BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE SHAREDTICKETS ADD COLUMN APPUSER VARCHAR;
ALTER TABLE SHAREDTICKETS ADD COLUMN PICKUPID INTEGER;

ALTER TABLE STOCKDIARY ADD COLUMN APPUSER VARCHAR;

--
-- UPDATE existing tables
--
UPDATE ROLES SET PERMISSIONS = $FILE{/com/openbravo/pos/templates/Role.Administrator.xml} WHERE id = '0';
UPDATE RESOURCES SET CONTENT = $FILE{/com/openbravo/pos/templates/Menu.Root.txt};

-- UPDATE App' version
UPDATE APPLICATIONS SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};

-- final script
DELETE FROM SHAREDTICKETS;