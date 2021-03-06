package tyson.example.com.greendaosample.model;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import tyson.example.com.greendaosample.model.Lease;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LEASE".
*/
public class LeaseDao extends AbstractDao<Lease, Long> {

    public static final String TABLENAME = "LEASE";

    /**
     * Properties of entity Lease.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Item = new Property(1, String.class, "item", false, "ITEM");
        public final static Property Comment = new Property(2, String.class, "comment", false, "COMMENT");
        public final static Property Leasedate = new Property(3, Long.class, "leasedate", false, "LEASEDATE");
        public final static Property Returndate = new Property(4, Long.class, "returndate", false, "RETURNDATE");
        public final static Property PersonId = new Property(5, Long.class, "personId", false, "PERSON_ID");
    };

    private DaoSession daoSession;

    private Query<Lease> person_LeasesQuery;

    public LeaseDao(DaoConfig config) {
        super(config);
    }
    
    public LeaseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LEASE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ITEM\" TEXT," + // 1: item
                "\"COMMENT\" TEXT," + // 2: comment
                "\"LEASEDATE\" INTEGER," + // 3: leasedate
                "\"RETURNDATE\" INTEGER," + // 4: returndate
                "\"PERSON_ID\" INTEGER);"); // 5: personId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LEASE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Lease entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String item = entity.getItem();
        if (item != null) {
            stmt.bindString(2, item);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(3, comment);
        }
 
        Long leasedate = entity.getLeasedate();
        if (leasedate != null) {
            stmt.bindLong(4, leasedate);
        }
 
        Long returndate = entity.getReturndate();
        if (returndate != null) {
            stmt.bindLong(5, returndate);
        }
 
        Long personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindLong(6, personId);
        }
    }

    @Override
    protected void attachEntity(Lease entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Lease readEntity(Cursor cursor, int offset) {
        Lease entity = new Lease( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // item
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // comment
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // leasedate
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // returndate
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // personId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Lease entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setItem(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setComment(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLeasedate(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setReturndate(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setPersonId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Lease entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Lease entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "leases" to-many relationship of Person. */
    public List<Lease> _queryPerson_Leases(Long personId) {
        synchronized (this) {
            if (person_LeasesQuery == null) {
                QueryBuilder<Lease> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PersonId.eq(null));
                person_LeasesQuery = queryBuilder.build();
            }
        }
        Query<Lease> query = person_LeasesQuery.forCurrentThread();
        query.setParameter(0, personId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getPersonDao().getAllColumns());
            builder.append(" FROM LEASE T");
            builder.append(" LEFT JOIN PERSON T0 ON T.\"PERSON_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Lease loadCurrentDeep(Cursor cursor, boolean lock) {
        Lease entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Person person = loadCurrentOther(daoSession.getPersonDao(), cursor, offset);
        entity.setPerson(person);

        return entity;    
    }

    public Lease loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Lease> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Lease> list = new ArrayList<Lease>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Lease> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Lease> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
