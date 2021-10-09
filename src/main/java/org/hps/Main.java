package org.hps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class Main {
    static RocksDB db1;
    static RocksDB db0;
    static Options options;
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main (String[] args) throws RocksDBException {
        log.info("in Main :)");
        RocksDB.loadLibrary();
        Main.options = new Options();
        Main.options.setCreateIfMissing(true);

        log.info("Opending DB0 and DB1");
        Main.db0 = RocksDB.open(Main.options, "/disk1/consumer00");
        Main.db1 = RocksDB.open(Main.options, "/disk2/consumer11");

        Long key = 0L;

        log.info("accessing DB, reading the keys");

        while(key <= 1000) {
            log.info("key {}", key );
            if(db0.get(key.toString().getBytes()) != null) {
                log.info("Database 0, value for key  {} == {}", key, new String (db0.get(key.toString().getBytes())));
            } else {
                log.info("Database 1, value for key  {} == {}", key, new String (db1.get(key.toString().getBytes())));
            }
            key++;
        }



      log.info("looping infin");
        //while(true);


    }

}
