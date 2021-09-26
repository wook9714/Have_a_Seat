package kr.co.jinwook.have_a_seat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.jinwook.have_a_seat.databinding.FragmentOrderSheetAllBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOrderSheetAll.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentOrderSheetAll : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding:FragmentOrderSheetAllBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentOrderSheetAllBinding.inflate(inflater,container,false)

        //테스트용 데이터 만드는 구간
        var testData:MutableList<OrderSheetItemForm> = mutableListOf()
        for(i in 0..100) {
            var testSet = OrderSheetItemForm("미결제","가게이름 ${i.toString()}","주문 품목에 대한 설명",10000)
            testData.add(testSet)
        }
        var adapter = OrderSheetRecyclerAdapter()
        adapter.listData = testData
        binding.orderSheetAllRecyclerView.adapter=adapter
        binding.orderSheetAllRecyclerView.layoutManager = LinearLayoutManager(context)




        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentOrderSheetAll.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentOrderSheetAll().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}