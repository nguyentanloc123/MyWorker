package com.example.myworker.Screen.Reg

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.Contanst.IMethodLoginAnReg
import com.example.myworker.Contanst.IValidate
import com.example.myworker.R
import com.example.myworker.Screen.OTP.OTPActivity
import com.example.myworker.UserData.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dang_ki_tho.*
import kotlinx.android.synthetic.main.activity_dang_ki_tho.btnReg
import kotlinx.android.synthetic.main.activity_dang_ki_tho.editemail
import kotlinx.android.synthetic.main.activity_dang_ki_tho.editusername
import kotlinx.android.synthetic.main.activity_dang_ki_tho.edtpasswork
import kotlinx.android.synthetic.main.activity_dang_ki_tho.edtsodienthoai

class DangKiThoActivity : AppCompatActivity() , IValidate,IMethodLoginAnReg{

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private  var count: Int = 0
    private  var sokinhnghiem: String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ki_tho)

        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        val languages = resources.getStringArray(R.array.Languages)
        if (edtnganhnghe != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, languages)
            edtnganhnghe.adapter = adapter

            edtnganhnghe.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    //Toast.makeText(this, getString(R.string.selected_item) + " " + "" + languages[position], Toast.LENGTH_SHORT).show()
                   // Toast.makeText(this@DangKiThoActivity,getString(R.string.selected_item)+ "" + languages[position], Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@DangKiThoActivity,getString(R.string.selected_item)+ "" + position, Toast.LENGTH_SHORT).show()
                    count = position

                    //  languages[position] = loaitho

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val kinhnghiem = resources.getStringArray(R.array.kinhnghiem)
        if (edtkinhnghiem != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, kinhnghiem)
            edtkinhnghiem.adapter = adapter

            edtkinhnghiem.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    //Toast.makeText(this, getString(R.string.selected_item) + " " + "" + languages[position], Toast.LENGTH_SHORT).show()
                    // Toast.makeText(this@DangKiThoActivity,getString(R.string.selected_item)+ "" + languages[position], Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@DangKiThoActivity,getString(R.string.selected_item)+ "" + position, Toast.LENGTH_SHORT).show()
                    sokinhnghiem = kinhnghiem[position].toString()

                    //  languages[position] = loaitho

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }



        btnReg.setOnClickListener {
            if(validateEqual() && !validate()) {
                RegFirebase()
            }
            else
            {
                Toast.makeText(this,"Không đăng kí được",Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun validate(): Boolean =(editusername.text.isNullOrEmpty() ||
    editemail.text.isNullOrEmpty() || edtpasswork.text.isNullOrEmpty() || edtsodienthoai.text.isNullOrEmpty() || edtpassworkre.text.isNullOrEmpty())

    override fun validateEqual(): Boolean = (edtpasswork.text.toString().equals(edtpassworkre.text.toString()))

    override fun LoginFirebase() {

    }
    override fun RegFirebase() {
        auth.createUserWithEmailAndPassword(editemail.text.toString(), edtpasswork.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(this,"Đăng kí thành công rồi nhen",Toast.LENGTH_LONG).show()
                        // luu vao database
                        val key = database.child("worker").push().key
                         val userDatabase = WorkerData(key.toString(),editusername.text.toString(),edtpasswork.text.toString(),editemail.text.toString(),edtsodienthoai.text.toString(), count,editfullname.text.toString(),sokinhnghiem,false)
                        val xacminh = DataXacMinh(key.toString(),false,0,0,false,false,false,false,false,false)
                        database.child("worker").child(editusername.text.toString()).setValue(userDatabase)
                        val thongtincanhan = DataChiTietThongTinTho("","","","")
                        val userWorkAdd = UserWorkAdd("","",false,false,"","",
                        "","","","","","","")
                        database.child("worker").child(editusername.text.toString()).child("thongtinxacminh").setValue(xacminh)
                        database.child("worker").child(editusername.text.toString()).child("datatemp").setValue(userWorkAdd)


                        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
                        with (sharedPref.edit()) {
                            putString(getString(R.string.usernameEmail), editemail.text.toString())
                            putString(getString(R.string.usernameUser),editusername.text.toString())
                            putString(getString(R.string.usernamePass),edtpasswork.text.toString())
                            commit()
                        }
                        val intent = Intent(this,
                            OTPActivity::class.java)
                        intent.putExtra("sodienthoaiOTP",edtsodienthoai.text.toString())
                        intent.putExtra("manhinh","1")
                        startActivity(intent)
                        // luu vao sharepreference

                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        // updateUI(null)
                    }

                    // ...
                }
    }
}