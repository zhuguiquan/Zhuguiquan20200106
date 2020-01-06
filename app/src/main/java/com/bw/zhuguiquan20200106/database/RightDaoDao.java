package com.bw.zhuguiquan20200106.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bw.zhuguiquan20200106.model.dao.RightDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RIGHT_DAO".
*/
public class RightDaoDao extends AbstractDao<RightDao, Void> {

    public static final String TABLENAME = "RIGHT_DAO";

    /**
     * Properties of entity RightDao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property RightJsons = new Property(0, String.class, "rightJsons", false, "RIGHT_JSONS");
    }


    public RightDaoDao(DaoConfig config) {
        super(config);
    }
    
    public RightDaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RIGHT_DAO\" (" + //
                "\"RIGHT_JSONS\" TEXT);"); // 0: rightJsons
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RIGHT_DAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RightDao entity) {
        stmt.clearBindings();
 
        String rightJsons = entity.getRightJsons();
        if (rightJsons != null) {
            stmt.bindString(1, rightJsons);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RightDao entity) {
        stmt.clearBindings();
 
        String rightJsons = entity.getRightJsons();
        if (rightJsons != null) {
            stmt.bindString(1, rightJsons);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public RightDao readEntity(Cursor cursor, int offset) {
        RightDao entity = new RightDao( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0) // rightJsons
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RightDao entity, int offset) {
        entity.setRightJsons(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(RightDao entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(RightDao entity) {
        return null;
    }

    @Override
    public boolean hasKey(RightDao entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}