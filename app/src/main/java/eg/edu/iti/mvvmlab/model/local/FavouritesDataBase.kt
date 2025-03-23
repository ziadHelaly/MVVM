package eg.edu.iti.mvvmlab.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eg.edu.iti.mvvmlab.model.models.Product

@Database(entities = [Product::class], version = 1)
abstract class FavouritesDataBase :RoomDatabase(){
    abstract fun getDao():ProductsDao
    companion object{
        @Volatile
        private var INSTANCE:FavouritesDataBase?=null
        fun getInstance(ctx: Context):FavouritesDataBase{
            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context = ctx,
                    FavouritesDataBase::class.java,
                    "products"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }
}