package edu.stanford.romualdt.simpleyelp

import android.content.Context
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions


class RestaurantAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :

    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount() = restaurants.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val ratingBar = itemView.findViewById<TextView>(R.id.ratingBar)
        private val tvNumReviews = itemView.findViewById<TextView>(R.id.tvNumReviews)
        private val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
        private val tvCategory = itemView.findViewById<TextView>(R.id.tvCategory)
        private val tvDistance = itemView.findViewById<TextView>(R.id.tvDistance)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)

        fun bind(restaurant: YelpRestaurant) {
            tvName.text = restaurant.name
            ratingBar.rating = restaurant.rating.toFloat()
            tvNumReviews.text = "${restaurant.numReviews} Reviews"
            tvAddress.text = restaurant.location.address
            tvCategory.text = restaurant.categories[0].title
            tvDistance.text = restaurant.displayDistance()
            tvPrice.text = restaurant.price
            Glide.with(context).load(restaurant.imageUrl).apply(RequestOptions().transform(
                CenterCrop(), RoundedCorners(20)
            )).into(itemView.imageView)
        }
    }

}
