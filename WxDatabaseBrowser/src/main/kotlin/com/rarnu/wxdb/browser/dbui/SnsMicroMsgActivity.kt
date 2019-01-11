package com.rarnu.wxdb.browser.dbui

import android.content.Intent
import com.rarnu.wxdb.browser.BaseTableActivity
import com.rarnu.wxdb.browser.R
import com.rarnu.wxdb.browser.database.DbSnsMicroMsg
import com.rarnu.wxdb.browser.ref.WxClassLoader
import com.rarnu.wxdb.browser.sns.NewParser
import com.rarnu.kt.android.*
import com.rarnu.wxdb.browser.BlobActivity
import com.rarnu.wxdb.browser.sns.ParseInfo

class SnsMicroMsgActivity : BaseTableActivity() {

    override fun initDb() = DbSnsMicroMsg()

    override fun titleResId() = R.string.title_sns


    override fun showBlobData(row: Int, col: Int, blob: ByteArray) {
        // currentTableName
        // col
        val clz = WxClassLoader.parserMap["${db.getDbName()}.$currentTableName.${currentTableField.get(col).str}"]
        ParseInfo.count = 0
        val str = NewParser(blob, clz).parseFrom().toString()
//        alert("$currentTableName [$row, $col]", str, resStr(R.string.btn_ok)) { }
        val intent = Intent(this, BlobActivity::class.java)
        intent.putExtra("BlobParseInfo", str)
        intent.putExtra("Title", "${db.getDbName()}.$currentTableName.${currentTableField.get(col).str}")
        startActivity(intent)
    }
}