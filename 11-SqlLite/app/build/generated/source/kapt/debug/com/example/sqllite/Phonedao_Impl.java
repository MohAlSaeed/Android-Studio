package com.example.sqllite;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class Phonedao_Impl implements Phonedao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Phone> __insertionAdapterOfPhone;

  private final EntityDeletionOrUpdateAdapter<Phone> __deletionAdapterOfPhone;

  private final SharedSQLiteStatement __preparedStmtOfDeleteALL;

  public Phonedao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPhone = new EntityInsertionAdapter<Phone>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Phone` (`id`,`name`,`price`,`quantity`,`available`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Phone value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getPrice());
        stmt.bindLong(4, value.getQuantity());
        final int _tmp;
        _tmp = value.getAvailable() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfPhone = new EntityDeletionOrUpdateAdapter<Phone>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Phone` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Phone value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteALL = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Phone";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Phone nphone) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPhone.insert(nphone);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Phone rphone) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPhone.handle(rphone);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteALL() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteALL.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteALL.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Phone>> getPhones() {
    final String _sql = "SELECT * FROM Phone";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Phone"}, false, new Callable<List<Phone>>() {
      @Override
      public List<Phone> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "available");
          final List<Phone> _result = new ArrayList<Phone>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Phone _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpPrice;
            _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final boolean _tmpAvailable;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAvailable);
            _tmpAvailable = _tmp != 0;
            _item = new Phone(_tmpId,_tmpName,_tmpPrice,_tmpQuantity,_tmpAvailable);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
