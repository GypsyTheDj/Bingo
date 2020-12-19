package com.normannuthu.bingo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.normannuthu.bingo.models.BoardSize
import com.normannuthu.bingo.models.MemoryCard
import com.normannuthu.bingo.models.MemoryGame
import com.normannuthu.bingo.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
    }

    private lateinit var rvBoard: RecyclerView
    private lateinit var movesTv: TextView
    private lateinit var pairsTv: TextView

    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: MemoryBoardAdapter
    private var boardSize: BoardSize = BoardSize.HARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBoard = findViewById(R.id.rvBoard)
        movesTv = findViewById(R.id.movesTv)
        pairsTv = findViewById(R.id.pairsTv)

        memoryGame = MemoryGame(boardSize)
        adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards, object: MemoryBoardAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                updateGmaeWithFlip(position)
            }
        })
        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }

    private fun updateGmaeWithFlip(position: Int) {
        memoryGame.flipCard(position)
        adapter.notifyDataSetChanged()
    }
}