package ar.com.therickandmortyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ar.com.data.domain.Character
import ar.com.therickandmortyapp.Constants
import ar.com.therickandmortyapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CharacterRecyclerAdapter(
    private val context: Context,
    var characterList: List<Character>,
    private val onItemSelected: (Character) -> Unit
) : RecyclerView.Adapter<CharacterRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvContainer = itemView.findViewById(R.id.cvContainer) as CardView
        val ivCover = itemView.findViewById(R.id.ivCover) as ImageView
        val tvName = itemView.findViewById(R.id.tvName) as TextView
        val tvStatus = itemView.findViewById(R.id.tvStatus) as TextView
        val tvSpecie = itemView.findViewById(R.id.tvSpecie) as TextView
        val tvType = itemView.findViewById(R.id.tvType) as TextView

        init {
            bindingAdapter
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        Glide.with(context)
            .load(character.image)
            .apply(RequestOptions().override(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT))
            .into(holder.ivCover)

        holder.tvName.text = character.name
        holder.tvStatus.text = character.status
        holder.tvSpecie.text = character.species
        holder.tvType.text =
            if (character.type != "") character.type else context.getString(R.string.default_value_option)

        holder.cvContainer.setOnClickListener { onItemSelected(character) }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}
