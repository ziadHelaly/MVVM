package eg.edu.iti.mvvmlab.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val brand: String?,
    val category: String?,
    val description: String?,
    val price: Double?,
    val rating: Double?,
    val thumbnail: String?,
    val title: String?,
    )
