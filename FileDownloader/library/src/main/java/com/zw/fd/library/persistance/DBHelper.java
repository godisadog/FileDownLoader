package com.zw.fd.library.persistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenwei on 2017/9/4.
 */

public class DBHelper {

    private DB db;
    private SQLiteDatabase database;
    private SparseArray<DownStatusModel> modelArr;
    private int columnCount;

    public DBHelper(Context context) {
        db = new DB(context, DB.DB_NAME, DB.VERSION);
        database = db.getWritableDatabase();

        refreshData();
    }

    private void refreshData() {
        modelArr = new SparseArray<>();
        Cursor cursor = database.query(DownStatusModel.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            columnCount = cursor.getColumnCount();

            while (cursor.moveToNext()) {
                DownStatusModel model = new DownStatusModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(DownStatusModel.ID)));
                model.setUrl(cursor.getString(cursor.getColumnIndex(DownStatusModel.URL)));
                model.setFilePath(cursor.getString(cursor.getColumnIndex(DownStatusModel.FILE_PATH)));
                model.setSofar(cursor.getInt(cursor.getColumnIndex(DownStatusModel.SOFAR)));
                model.setTotal(cursor.getInt(cursor.getColumnIndex(DownStatusModel.TOTAL)));
                model.setStatus(cursor.getInt(cursor.getColumnIndex(DownStatusModel.STATUS)));
                model.seteTag(cursor.getString(cursor.getColumnIndex(DownStatusModel.ETAG)));
                model.setErrMsg(cursor.getString(cursor.getColumnIndex(DownStatusModel.ERR_MSG)));

                modelArr.put(model.getId(), model);
            }
        }

    }

    public DownStatusModel query(int id) {
        return modelArr.get(id);
    }

    public List<DownStatusModel> queryAll() {
        int size = modelArr.size();
        List<DownStatusModel> modelList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            modelList.add(modelArr.get(modelArr.keyAt(i)));
        }

        return modelList;
    }

    public void insertOrUpdateValue(DownStatusModel model) {
        if (modelArr.get(model.getId()) != null) {
            database.update(DownStatusModel.TABLE_NAME, toContentValues(model), DownStatusModel.ID + "= ?", new String[]{String.valueOf(model.getId())});
        } else {
            database.insert(DownStatusModel.TABLE_NAME, null, toContentValues(model));
        }
        modelArr.put(model.getId(), model);
    }

    public int delete(int id) {
        modelArr.delete(id);
        return database.delete(DownStatusModel.TABLE_NAME, DownStatusModel.ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void delete(DownStatusModel model) {
        if (model != null) {
            delete(model.getId());
        }
    }

    public void deleteAll() {
        modelArr.clear();
        database.delete(DownStatusModel.TABLE_NAME, null, null);
    }

    public void close() {
        database.close();
    }

    private ContentValues toContentValues(DownStatusModel model) {
        ContentValues contentValues = null;
        if (model != null) {
            contentValues = new ContentValues(columnCount);
            contentValues.put(DownStatusModel.ID, model.getId());
            contentValues.put(DownStatusModel.URL, model.getUrl());
            contentValues.put(DownStatusModel.FILE_PATH, model.getFilePath());
            contentValues.put(DownStatusModel.SOFAR, model.getSofar());
            contentValues.put(DownStatusModel.TOTAL, model.getTotal());
            contentValues.put(DownStatusModel.STATUS, model.getStatus());
            contentValues.put(DownStatusModel.ETAG, model.geteTag());
            contentValues.put(DownStatusModel.ERR_MSG, model.getErrMsg());
        }
        return contentValues;
    }

}
