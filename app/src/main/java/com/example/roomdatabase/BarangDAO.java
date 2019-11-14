package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BarangDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBarang(Barang barang);

    @Query("SELECT * FROM tbarang")
    Barang[] selectAllBarangs();

    @Delete
    int deleteBarang(Barang barang);

    @Update
    int updateBarang(Barang barang);
}
