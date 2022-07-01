package com.example.first_app

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.first_app.databinding.FragmentGalleryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GalleryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    //private var param1: String? = null
    //private var param2: String? = null

    //val REQUEST_GET_IMAGE = 105
    //val PERMISSIONS_REQUEST_CODE = 100
    //var REQUIRED_PERMISSIONS = arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE)

    //바인딩 객체 타입에 ?를 붙여서 null 허용(ondestory될 때 완벽하게 destroy 하기 위해)
    private var mBinding: FragmentGalleryBinding?= null
    //매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!
    val datas = mutableListOf<Photo>()

    // 이미지 데이터 리스트
    var list = ArrayList<Uri>()

    // 1.Context를 액티비티로 형변환해서 할당
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        mainActivity = context as MainActivity
    }




   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val reAdapter = MultiImageAdapter(list,mainActivity)
        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK ){
                list.clear()
                if (it.data?.clipData != null) { // 사진 여러개 선택한 경우
                    val count = it.data!!.clipData!!.itemCount
                    if (count > 20) {
                        Toast.makeText(getContext(), "사진은 20장까지 선택 가능합니다.", Toast.LENGTH_LONG)
                    }
                    for (i in 0 until count) {
                        val imageUri = it.data!!.clipData!!.getItemAt(i).uri
                        list.add(imageUri)
                    }

                } else { // 단일 선택
                    it.data?.data?.let { uri ->
                        val imageUri : Uri? = it.data?.data
                        if (imageUri != null) {
                            list.add(imageUri)
                        }
                    }
                }
                reAdapter.notifyDataSetChanged()

            }
        }

        // Inflate the layout for this fragment
        mBinding = FragmentGalleryBinding.inflate(inflater, container, false)
        //갤러리 버튼이 클릭되면 갤러리를 연다
        binding.btnGallery.setOnClickListener{
            var intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            //startActivityForResult가 삭제! -> 대체 registerForActivtyResult()
            startForResult.launch(intent)
        }
        //context : 액티비티에 객체 붙일 때 사용
        val layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false) // requireContext : context가 null이 아님을 보장
        //binding.recyclerView.scrollToPosition(list.size-1)
        binding.recyclerView.adapter = reAdapter
        binding.recyclerView.layoutManager = layoutManager
        return binding.root

    }

    override fun onDestroyView() {
        //onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroyView()
    }

}