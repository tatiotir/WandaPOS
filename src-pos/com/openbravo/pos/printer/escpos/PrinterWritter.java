//    Wanda POS  - Africa's Gift to the World
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

package com.openbravo.pos.printer.escpos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author JG uniCenta
 */
public abstract class PrinterWritter {
    
    private boolean initialized = false;

    private ExecutorService exec;
    
    /**
     *
     */
    public PrinterWritter() {
        exec = Executors.newSingleThreadExecutor();
    }
    
    /**
     *
     * @param data
     */
    protected abstract void internalWrite(byte[] data);

    /**
     *
     */
    protected abstract void internalFlush();

    /**
     *
     */
    protected abstract void internalClose();
    
    /**
     *
     * @param data
     */
    public void init(final byte[] data) {
        if (!initialized) {
            write(data);
            initialized = true;
        }
    }
       
    /**
     *
     * @param sValue
     */
    public void write(String sValue) {
        write(sValue.getBytes());
    }

    /**
     *
     * @param data
     */
    public void write(final byte[] data) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                internalWrite(data);
            }
        });
    }
    
    /**
     *
     */
    public void flush() {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                internalFlush();
            }
        });
    }
    
    /**
     *
     */
    public void close() {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                internalClose();
            }
        });
        exec.shutdown();
    }
}
