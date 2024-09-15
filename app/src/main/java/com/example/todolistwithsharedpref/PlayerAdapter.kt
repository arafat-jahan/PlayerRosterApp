package com.example.todolistwithsharedpref

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter(
    private val players: List<Player>,
    private val editPlayer: (Player) -> Unit,
    private val deletePlayer: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int = players.size

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerImage: ImageView = itemView.findViewById(R.id.playerImage)
        private val playerName: TextView = itemView.findViewById(R.id.playerName)
        private val playerInfo: TextView = itemView.findViewById(R.id.playerInfo)
        private val playerAge: TextView = itemView.findViewById(R.id.playerAge)
        private val playerPosition: TextView = itemView.findViewById(R.id.playerPosition)
        private val editButton: ImageView = itemView.findViewById(R.id.editBtn)
        private val deleteButton: ImageView = itemView.findViewById(R.id.deleteBtn)

        fun bind(player: Player) {
            playerImage.setImageResource(player.imageResId)
            playerName.text = player.name
            playerInfo.text = player.info
            playerAge.text = "Age: ${player.age}"
            playerPosition.text = "Position: ${player.position}"

            editButton.setOnClickListener { editPlayer(player) }
            deleteButton.setOnClickListener { deletePlayer(player) }
        }
    }
}
