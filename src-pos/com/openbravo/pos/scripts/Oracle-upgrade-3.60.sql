--    Wanda POS - Africa's Gift to the World
--    Copyright (C) 2014-2015 Wanda POS
--    http://www.unicenta.net
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

-- Database upgrade script for Oracle
-- v3.60 - v3.70

--
-- UPDATE existing tables
--

--
-- ALTER existing tables
--

--
-- INSERT INTO tables
--
INSERT INTO PRODUCTS(ID, REFERENCE, CODE, NAME, CATEGORY, TAXCAT, ISSERVICE) 
VALUES ('xxx999_999xxx_x9x9x9', 'xxx999', 'xxx999', '***', '000', '001', 1);

INSERT INTO PRODUCTS_CAT(PRODUCT) 
VALUES ('xxx999_999xxx_x9x9x9');


-- UPDATE App' version
UPDATE APPLICATIONS SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};

-- final script
DELETE FROM SHAREDTICKETS;