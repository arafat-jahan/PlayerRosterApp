package com.example.todolistwithsharedpref

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var playerAdapter: PlayerAdapter
    private val playerList = mutableListOf<Player>()
    private var currentPlayer: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        playerAdapter = PlayerAdapter(playerList, this::editPlayer, this::deletePlayer)
        recyclerView.adapter = playerAdapter


        val playerNameEt: EditText = findViewById(R.id.todoEt)
        val playerInfoEt: EditText = findViewById(R.id.todoEt2)
        val playerAgeEt: EditText = findViewById(R.id.todoEt3)
        val playerPositionEt: EditText = findViewById(R.id.todoEt4)
        val saveBtn: Button = findViewById(R.id.saveBtn)


        val samplePlayers = listOf(
            Player("Lionel Messi", "Argentine football player", R.drawable.images, 36, "Forward"),
            Player("Cristiano Ronaldo", "Portuguese football player", R.drawable.ronaldo, 39, "Forward"),
            Player("Kylian Mbappe", "French football player", R.drawable.mbappe, 25, "Forward"),
            Player("Robert Lewandowski", "Polish football player", R.drawable.lewandowski, 35, "Forward"),
            Player("Mohamed Salah", "Egyptian football player", R.drawable.salah, 32, "Forward"),
            Player("Neymar Jr.", "Brazilian football player", R.drawable.neymar, 31, "Forward"),
            Player("Eden Hazard", "Belgian football player", R.drawable.hazard, 33, "Midfielder"),
            Player("Virgil Van Dijk", "Dutch football player", R.drawable.vandijk, 33, "Defender"),
            Player("Manuel Neuer", "German football player", R.drawable.neuer, 38, "Goalkeeper")
        )



        playerList.addAll(samplePlayers)
        playerAdapter.notifyDataSetChanged()

        saveBtn.setOnClickListener {
            val playerName = playerNameEt.text.toString()
            val playerInfo = playerInfoEt.text.toString()
            val playerAge = playerAgeEt.text.toString().toIntOrNull() ?: 0
            val playerPosition = playerPositionEt.text.toString()
            val imageResId = R.drawable.images // Replace with logic to select different images if needed

            if (playerName.isNotEmpty() && playerInfo.isNotEmpty() && playerAge > 0 && playerPosition.isNotEmpty()) {
                val player = Player(playerName, playerInfo, imageResId, playerAge, playerPosition)
                if (currentPlayer == null) {

                    playerList.add(player)
                    playerAdapter.notifyItemInserted(playerList.size - 1)
                } else {

                    val index = playerList.indexOf(currentPlayer)
                    if (index != -1) {
                        playerList[index] = player
                        playerAdapter.notifyItemChanged(index)
                        currentPlayer = null
                    }
                }
                playerNameEt.text.clear()
                playerInfoEt.text.clear()
                playerAgeEt.text.clear()
                playerPositionEt.text.clear()
            }
        }
    }

    private fun editPlayer(player: Player) {

        val playerNameEt: EditText = findViewById(R.id.todoEt)
        val playerInfoEt: EditText = findViewById(R.id.todoEt2)
        val playerAgeEt: EditText = findViewById(R.id.todoEt3)
        val playerPositionEt: EditText = findViewById(R.id.todoEt4)

        playerNameEt.setText(player.name)
        playerInfoEt.setText(player.info)
        playerAgeEt.setText(player.age.toString())
        playerPositionEt.setText(player.position)
        currentPlayer = player
    }

    private fun deletePlayer(player: Player) {
        val index = playerList.indexOf(player)
        if (index != -1) {
            playerList.removeAt(index)
            playerAdapter.notifyItemRemoved(index)
        }
    }
}
