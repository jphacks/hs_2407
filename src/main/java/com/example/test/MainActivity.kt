package com.example.test
import android.app.AppOpsManager
import android.os.Bundle
import android.app.usage.UsageStatsManager
import android.content.Context
import java.util.Calendar
import java.io.FileOutputStream
import android.app.usage.UsageStats
import android.provider.Settings
import android.content.Intent
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Environment
import com.opencsv.CSVWriter
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.StringWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!hasUsageStatsPermission()) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        } else {
            val usageTimeTextViewN: TextView = findViewById(R.id.hiddenMessageN)
            val packageNameN = "com.netflix.mediaclient"
            val usageTimeN = getAppUsageTime(packageNameN)
            val hoursN = (usageTimeN / (1000 * 60 * 60)) + (usageTimeN / (1000 * 60)) / 60
            val minutesN = (usageTimeN / (1000 * 60)) % 60
            usageTimeTextViewN.text =
                "アプリ $packageNameN の稼働時間: $hoursN 時間 $minutesN 分\nアプリ $packageNameN の稼働時間: $hoursN 時間 $minutesN 分"
            val usageTimeTextViewU: TextView = findViewById(R.id.hiddenMessageU)
            val packageNameU = "jp.unext.mediaplayer"
            val usageTimeU = getAppUsageTime(packageNameU)
            val hoursU = (usageTimeU / (1000 * 60 * 60)) + (usageTimeU / (1000 * 60)) / 60
            val minutesU = (usageTimeU / (1000 * 60)) % 60
            usageTimeTextViewU.text =
                "アプリ $packageNameU の稼働時間: $hoursU 時間 $minutesU 分\nアプリ $packageNameU の稼働時間: $hoursU 時間 $minutesU 分"
            val usageTimeTextViewS: TextView = findViewById(R.id.hiddenMessageS)
            val packageNameS = "com.spotify.music"
            val usageTimeS = getAppUsageTime(packageNameS)
            val hoursS = (usageTimeS / (1000 * 60 * 60)) + (usageTimeS / (1000 * 60)) / 60
            val minutesS = (usageTimeS / (1000 * 60)) % 60
            usageTimeTextViewS.text =
                "アプリ $packageNameS の稼働時間: $hoursS 時間 $minutesS 分\nアプリ $packageNameS の稼働時間: $hoursS 時間 $minutesS 分"
            val strWriter = StringWriter()
            val csvWriter = CSVWriter(strWriter)
            val randid=Math.random()
            csvWriter.writeNext(arrayOf("$randid","${usageTimeN}N"))


        }

        val button1 = findViewById<Button>(R.id.netflix)
        val hiddenInfoN = findViewById<TextView>(R.id.hiddenMessageN)

        button1.setOnClickListener {
            if (hiddenInfoN.visibility == View.GONE) {
                hiddenInfoN.visibility = View.VISIBLE
                // 下方向にスライド
                val slideDown = TranslateAnimation(0f, 0f, -hiddenInfoN.height.toFloat(), 0f)
                slideDown.duration = 0
                hiddenInfoN.startAnimation(slideDown)
            } else {
                // 上方向にスライド
                val slideUp = TranslateAnimation(0f, 0f, 0f, -hiddenInfoN.height.toFloat())
                slideUp.duration = 0
                hiddenInfoN.startAnimation(slideUp)
                hiddenInfoN.visibility = View.GONE
            }
            val button2 = findViewById<Button>(R.id.unext)
            val hiddenInfoU = findViewById<TextView>(R.id.hiddenMessageU)

            button2.setOnClickListener {
                if (hiddenInfoU.visibility == View.GONE) {
                    hiddenInfoU.visibility = View.VISIBLE
                    // 下方向にスライド
                    val slideDown = TranslateAnimation(0f, 0f, -hiddenInfoU.height.toFloat(), 0f)
                    slideDown.duration = 0
                    hiddenInfoU.startAnimation(slideDown)
                } else {
                    // 上方向にスライド
                    val slideUp = TranslateAnimation(0f, 0f, 0f, -hiddenInfoU.height.toFloat())
                    slideUp.duration = 0
                    hiddenInfoU.startAnimation(slideUp)
                    hiddenInfoU.visibility = View.GONE
                }

            }

            val button3 = findViewById<Button>(R.id.disney)
            val hiddenInfoDi = findViewById<TextView>(R.id.hiddenMessageDi)

            button3.setOnClickListener {
                if (hiddenInfoDi.visibility == View.GONE) {
                    hiddenInfoDi.visibility = View.VISIBLE
                    // 下方向にスライド
                    val slideDown = TranslateAnimation(0f, 0f, -hiddenInfoDi.height.toFloat(), 0f)
                    slideDown.duration = 0
                    hiddenInfoDi.startAnimation(slideDown)
                } else {
                    // 上方向にスライド
                    val slideUp = TranslateAnimation(0f, 0f, 0f, -hiddenInfoDi.height.toFloat())
                    slideUp.duration = 0
                    hiddenInfoDi.startAnimation(slideUp)
                    hiddenInfoDi.visibility = View.GONE
                }

            }

            val button4 = findViewById<Button>(R.id.spotify)
            val hiddenInfoS = findViewById<TextView>(R.id.hiddenMessageS)

            button4.setOnClickListener {
                if (hiddenInfoS.visibility == View.GONE) {
                    hiddenInfoS.visibility = View.VISIBLE
                    // 下方向にスライド
                    val slideDown = TranslateAnimation(0f, 0f, -hiddenInfoS.height.toFloat(), 0f)
                    slideDown.duration = 0
                    hiddenInfoS.startAnimation(slideDown)
                } else {
                    // 上方向にスライド
                    val slideUp = TranslateAnimation(0f, 0f, 0f, -hiddenInfoS.height.toFloat())
                    slideUp.duration = 0
                    hiddenInfoS.startAnimation(slideUp)
                    hiddenInfoS.visibility = View.GONE
                }

            }
            val button5 = findViewById<Button>(R.id.dazn)
            val hiddenInfoDa = findViewById<TextView>(R.id.hiddenMessageDa)

            button5.setOnClickListener {
                if (hiddenInfoDa.visibility == View.GONE) {
                    hiddenInfoDa.visibility = View.VISIBLE
                    // 下方向にスライド
                    val slideDown = TranslateAnimation(0f, 0f, -hiddenInfoDa.height.toFloat(), 0f)
                    slideDown.duration = 0
                    hiddenInfoDa.startAnimation(slideDown)
                } else {
                    // 上方向にスライド
                    val slideUp = TranslateAnimation(0f, 0f, 0f, -hiddenInfoDa.height.toFloat())
                    slideUp.duration = 0
                    hiddenInfoDa.startAnimation(slideUp)
                    hiddenInfoDa.visibility = View.GONE
                }

            }
            val button6 = findViewById<Button>(R.id.applemusic)
            val hiddenInfoA = findViewById<TextView>(R.id.hiddenMessageA)

            button6.setOnClickListener {
                if (hiddenInfoA.visibility == View.GONE) {
                    hiddenInfoA.visibility = View.VISIBLE
                    // 下方向にスライド
                    val slideDown = TranslateAnimation(0f, 0f, -hiddenInfoA.height.toFloat(), 0f)
                    slideDown.duration = 0
                    hiddenInfoA.startAnimation(slideDown)
                } else {
                    // 上方向にスライド
                    val slideUp = TranslateAnimation(0f, 0f, 0f, -hiddenInfoA.height.toFloat())
                    slideUp.duration = 0
                    hiddenInfoA.startAnimation(slideUp)
                    hiddenInfoA.visibility = View.GONE
                }

            }
        }

    }

    private fun hasUsageStatsPermission(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun getAppUsageTime(packageName: String): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)

        val usageStatsManager =
            getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        // 過去7日間の使用状況データを取得
        val usageStatsList = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            calendar.timeInMillis,
            System.currentTimeMillis()
        )
        var totalTime = 0L
        for (usageStats in usageStatsList) {
            if (usageStats.packageName == packageName) {
                totalTime += usageStats.totalTimeForegroundServiceUsed
            }
        }
        return totalTime
    }
    data class UserData(
        val userId: String,
        val usageTime: Long,
        val appName: String
    )

    fun saveDataAsCsv(data: List<UserData>, fileName: String) {
        val csvHeader = "UserId,UsageTime,AppName\n" // CSVのヘッダー
        val csvContent = StringBuilder(csvHeader)

        // データをCSV形式に追加
        data.forEach { userData ->
            csvContent.append("${userData.userId},${userData.usageTime},${userData.appName}\n")
        }

        // CSVファイルを内部ストレージに保存
        val fileOutputStream: FileOutputStream
        try {
            // 内部ストレージのファイルパスを取得
            val file = File(filesDir, fileName)
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(csvContent.toString().toByteArray(Charsets.UTF_8))
            fileOutputStream.close()
            println("CSVファイルを保存しました: ${file.absolutePath}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun valuetime(yourtime: Long, avgtime: Long,monthcost:Int) {
        if(yourtime>avgtime){
            var yen=monthcost/30*7*(yourtime/avgtime)
        }
    }
}