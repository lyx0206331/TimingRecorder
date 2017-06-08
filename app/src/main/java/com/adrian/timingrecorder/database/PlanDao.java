package com.adrian.timingrecorder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.adrian.timingrecorder.database.DBHelper;
import com.adrian.timingrecorder.pojo.PlanInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranqing on 2017/5/29.
 */

public class PlanDao {
    private static final String TAG = "DAO";

    private final String[] PLAN_COLUMNS = new String[]{"_id", "planName", "createTime", "planStartTime", "planStopTime", "status", "description"};

    private Context context;
    private DBHelper helper;

    public PlanDao(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    /**
     * 检查是否有数据
     *
     * @return
     */
    public boolean isDataExists() {
        int count = 0;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = helper.getReadableDatabase();
            cursor = db.query(DBHelper.TABLE_NAME, new String[]{"COUNT(_id)"}, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) {
                return true;
            }

        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    public void testDB() {
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (planName, createTime, planStartTime, planStopTime, status, description)" +
                    " values('计划一', 123123123123, 12432423423, 12432423423, 0, 'abcdedgasdfasd;l;ljk')");
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (planName, createTime, planStartTime, planStopTime, status, description)" +
                    " values('计划二', 123123123123, 12432423423, 12432423423, 0, 'abcdedgasdfasd;l;ljk')");
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (planName, createTime, planStartTime, planStopTime, status, description)" +
                    " values('计划三', 123123123123, 12432423423, 12432423423, 0, 'abcdedgasdfasd;l;ljk')");
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (planName, createTime, planStartTime, planStopTime, status, description)" +
                    " values('计划四', 123123123123, 12432423423, 12432423423, 0, 'abcdedgasdfasd;l;ljk')");
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (planName, createTime, planStartTime, planStopTime, status, description)" +
                    " values('计划五', 123123123123, 12432423423, 12432423423, 0, 'abcdedgasdfasd;l;ljk')");

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<PlanInfo> getAllData() {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = helper.getReadableDatabase();
            cursor = db.query(DBHelper.TABLE_NAME, PLAN_COLUMNS, null, null, null, null, null);
            if (cursor.getCount() > 0) {
                List<PlanInfo> planList = new ArrayList<>();
                while (cursor.moveToNext()) {
                    planList.add(parsePlan(cursor));
                }
                return planList;
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    /**
     * 根据状态查询数据
     *
     * @param status 0表示未执行，1表示已执行
     * @return
     */
    public List<PlanInfo> getPlanByStatus(int status) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = helper.getReadableDatabase();
            cursor = db.query(DBHelper.TABLE_NAME, PLAN_COLUMNS, "status = ?", new String[]{status + ""}, null, null, null);
            if (cursor.getCount() > 0) {
                List<PlanInfo> plans = new ArrayList<>();
                while (cursor.moveToNext()) {
                    plans.add(parsePlan(cursor));
                }
                return plans;
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    /**
     * 根据创建时间查询数据
     *
     * @param createTime 创建时间
     * @return
     */
    public PlanInfo getPlanByTime(long createTime) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = helper.getReadableDatabase();
            cursor = db.query(DBHelper.TABLE_NAME, PLAN_COLUMNS, "createTime = ?", new String[]{createTime + ""}, null, null, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToNext()) {
                    return parsePlan(cursor);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    public PlanInfo getPlanById(long id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = helper.getReadableDatabase();
            cursor = db.query(DBHelper.TABLE_NAME, PLAN_COLUMNS, "_id = ?", new String[]{id + ""}, null, null, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToNext()) {
                    return parsePlan(cursor);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    /**
     * 插入数据
     *
     * @param name
     * @param planStartTime
     * @param planStopTime
     * @param status
     * @param description
     * @return 返回插入id
     */
    public long insertData(String name, long planStartTime, long planStopTime, int status, String description) {
        SQLiteDatabase db = null;

        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("planName", name);
            values.put("createTime", System.currentTimeMillis());
            values.put("planStartTime", planStartTime);
            values.put("planStopTime", planStopTime);
            values.put("status", status);
            values.put("description", description);
            long id = db.insertOrThrow(DBHelper.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
            return id;

        } catch (SQLiteConstraintException e) {
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }

        return -1;
    }

    /**
     * 删除计划
     *
     * @param id
     * @return
     */
    public boolean deletePlan(int id) {
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.delete(DBHelper.TABLE_NAME, "_id = ?", new String[]{id + ""});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 更新数据
     *
     * @param id
     * @param name
     * @param planStartTime
     * @param planStopTime
     * @param status
     * @param description
     * @return
     */
    public boolean updatePlan(int id, String name, long planStartTime, long planStopTime, int status, String description) {
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            ContentValues values = new ContentValues();
            if (!TextUtils.isEmpty(name)) {
                values.put("planName", name);
            }
            if (!TextUtils.isEmpty(description)) {
                values.put("description", description);
            }
            values.put("planStartTime", planStartTime);
            values.put("planStopTime", planStopTime);
            values.put("status", status);
            db.update(DBHelper.TABLE_NAME, values, "_id = ?", new String[]{id + ""});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 清空数据
     *
     * @return
     */
    public boolean clearData() {
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.delete(DBHelper.TABLE_NAME, null, null);
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    private PlanInfo parsePlan(Cursor cursor) {
        PlanInfo info = new PlanInfo();
        info.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        info.setName(cursor.getString(cursor.getColumnIndex("planName")));
        info.setCreateTime(cursor.getLong(cursor.getColumnIndex("createTime")));
        info.setPlanStartTime(cursor.getLong(cursor.getColumnIndex("planStartTime")));
        info.setPlanStopTime(cursor.getLong(cursor.getColumnIndex("planStopTime")));
        info.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
        info.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        return info;
    }
}
