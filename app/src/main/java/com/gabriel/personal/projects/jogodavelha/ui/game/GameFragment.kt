package com.gabriel.personal.projects.jogodavelha.ui.game

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gabriel.personal.projects.jogodavelha.R
import com.gabriel.personal.projects.jogodavelha.databinding.GameFragmentBinding

class GameFragment : Fragment() {

    private val gameViewModel by viewModels<GameViewModel>()

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            button1.setOnClickListener { play(it, 1) }
            button2.setOnClickListener { play(it, 2) }
            button3.setOnClickListener { play(it, 3) }
            button4.setOnClickListener { play(it, 4) }
            button5.setOnClickListener { play(it, 5) }
            button6.setOnClickListener { play(it, 6) }
            button7.setOnClickListener { play(it, 7) }
            button8.setOnClickListener { play(it, 8) }
            button9.setOnClickListener { play(it, 9) }
        }

        gameViewModel.gameFinishEvent.observe(viewLifecycleOwner, { finished ->
            if (finished) {
                Toast.makeText(activity, "${gameViewModel.currentPlayer.value?.name} win", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_gameFragment_self)
            }
        })

    }

    private fun play(button: View, position: Int) {
        button.isEnabled = false

        when (gameViewModel.currentPlayer.value) {
            Player.First -> {
                button.setBackgroundColor(Color.BLUE)
            }
            Player.Second -> {
                button.setBackgroundColor(Color.GREEN)
            }
        }
        gameViewModel.play(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}