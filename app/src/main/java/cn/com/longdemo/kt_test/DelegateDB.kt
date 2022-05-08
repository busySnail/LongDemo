package cn.com.longdemo.kt_test

import android.util.Log

interface IDB {
    fun save()
}

class SqlDB : IDB {
    override fun save() {
        Log.d("TestML", "SqlDB-save: ")
    }

}

class OracleDB : IDB {
    override fun save() {
        Log.d("TestML", "OracleDB-save: ")
    }
}

class UniversalDB(db: IDB) : IDB by db {

}