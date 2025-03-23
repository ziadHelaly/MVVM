package eg.edu.iti.mvvmlab.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eg.edu.iti.mvvmlab.model.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Query("select * from products")
    fun getAll():Flow<List<Product>>

    @Query("select * from products where id=:id")
    suspend fun getProduct(id:Int):Product

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: Product) :Long

    @Delete
    suspend fun removeItem(product: Product):Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALl(products: List<Product>) :List<Long>
}