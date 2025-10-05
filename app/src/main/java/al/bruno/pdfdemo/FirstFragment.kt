package al.bruno.pdfdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import al.bruno.pdfdemo.databinding.FragmentFirstBinding
import android.net.Uri

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pdfView.webViewClient = PDFWebViewClient()
        binding.pdfView.loadUrl("https://pdftron.s3.amazonaws.com/downloads/pl/PDFTRON_mobile_about.pdf")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}