package com.example.lyx0412

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener,
    View.OnTouchListener {

    lateinit var gDetector: GestureDetector
    var PictureNo:Int = 0  //目前顯示第幾張圖
    var TotalPictures:Int = 6 //總共幾張圖片(假設僅顯示pu0-pu3)

    fun ShowPicture() {
        /*
        when (PictureNo) {
            0 -> img.setImageResource(R.drawable.p0)
            1 -> img.setImageResource(R.drawable.p1)
            2 -> img.setImageResource(R.drawable.p2)
            3 -> img.setImageResource(R.drawable.p3)
            4 -> img.setImageResource(R.drawable.p4)
            5 -> img.setImageResource(R.drawable.p5)
        }
        */
        txv.text = PictureNo.toString()
        var res:Int = getResources().getIdentifier("p" + PictureNo.toString(),
            "drawable", getPackageName())
        img.setImageResource(res)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)
        img.setOnTouchListener(this)
        var res:Int = -1
        var countDrawables:Int = -1
        while (res != 0) {
            countDrawables++;
            res = getResources().getIdentifier("p" + countDrawables.toString(),
                "drawable", getPackageName());
        }
        TotalPictures = countDrawables
    }

    /*override fun onTouchEvent(event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }*/

    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {    //每次點擊時發生（相當於ACTION_DOWN）
        //txv.text = "按下"
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {    //使用者點擊後，停留較長一點時間，沒有滑動也還沒放開時發生
        //txv.text = "按下後無後續動作"
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {     //短按
        //txv.text = "短按"
        PictureNo = 0
        ShowPicture()
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY:  Float): Boolean {    //拖曳
        /*txv.text = "拖曳\nx1y1: " +  e1?.getX().toString() + ", " + e1?.getY().toString() +
                "\nx2y2: " + e2?.getX().toString() + ", " + e2?.getY().toString()*/
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {    //長按
        //txv.text = "長按"
        PictureNo = TotalPictures - 1
        ShowPicture()
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {   //快滑
        /*txv.text = "快滑\nx1y1: " + e1?.getX().toString() + ", " + e1?.getY().toString() +
                "\nx2y2: " + e2?.getX().toString() + ", " + e2?.getY().toString() +
                "\nX軸Y軸速度:" + velocityX.toString() + ", " +  velocityY.toString()*/
        if (e1!!.getX() < e2!!.getX()){  //向右快滑
            PictureNo++
            if (PictureNo == TotalPictures) {PictureNo = 0}
        }
        else{     //向左快滑
            PictureNo--;
            if (PictureNo < 0) {PictureNo = TotalPictures - 1 }
        }
        ShowPicture()

        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {    //用來判定該次點擊是SingleTap還是DoubleTap
        return true
    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {   //在雙擊的第二下，Touch down時觸發
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {    //通知DoubleTap手勢中的事件，雙擊的第二下Touch down和up都會觸發，可用p0.getAction()區分
        //txv.text = "連續點兩下"
        return true
    }
}
