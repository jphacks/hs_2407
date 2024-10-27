package com.example.test
import android.app.AppOpsManager
import android.os.Bundle
import android.app.usage.UsageStatsManager
import android.content.Context
import java.util.Calendar
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import android.app.usage.UsageStats
import android.provider.Settings
import android.content.Intent
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Environment
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.io.DataOutputStream
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //使用情報へのアクセス権限を取得
        if (!hasUsageStatsPermission()) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        } else {
            //ボタンを押した際に表示されるアプリ毎の情報を表示
            val usageTimeTextViewS: TextView = findViewById(R.id.hiddenMessageS)
            val packageNameS = "com.spotify.music"
            val usageTimeS = getAppUsageTime(packageNameS)
            val hoursS = (usageTimeS / (1000 * 60 * 60)) + (usageTimeS / (1000 * 60)) / 60
            val minutesS = (usageTimeS / (1000 * 60)) % 60
            val AveS=28
            val costS=980
            val valueS=calculateLossAndProfit(AveS.toDouble(),hoursS.toDouble(),costS.toDouble())
            usageTimeTextViewS.text =
                "Spotify の稼働時間: $hoursS 時間 $minutesS 分 (週)\nみんなの平均利用時間: $AveS 時間\n評価 ${generateSubscriptionMessage(AveS.toDouble(),hoursS.toDouble(),costS.toDouble())} \nコメント: あなたは  ${valueS.second} 円得しています"
            val usageTimeTextViewN: TextView = findViewById(R.id.hiddenMessageN)
            val packageNameN = "com.netflix.mediaclient"
            val usageTimeN = getAppUsageTime(packageNameN)
            val hoursN = (usageTimeN / (1000 * 60 * 60)) + (usageTimeN / (1000 * 60)) / 60
            val minutesN = (usageTimeN / (1000 * 60)) % 60
            val AveN=9
            val costN=1590
            val valueN=calculateLossAndProfit(AveN.toDouble(),hoursN.toDouble(),costN.toDouble())
            usageTimeTextViewN.text =
                "Netflix の稼働時間: 15 時間 $minutesN 分　(週)\nみんなの平均利用時間: $AveN 時間\n評価 ${generateSubscriptionMessage(AveN.toDouble(),hoursN.toDouble(),costN.toDouble())} \nコメント: あなたは  ${valueN.second} 円得しています"
            val usageTimeTextViewU: TextView = findViewById(R.id.hiddenMessageU)
            val packageNameU = "jp.unext.mediaplayer"
            val usageTimeU = getAppUsageTime(packageNameU)
            val hoursU = (usageTimeU / (1000 * 60 * 60)) + (usageTimeU / (1000 * 60)) / 60
            val minutesU = (usageTimeU / (1000 * 60)) % 60
            usageTimeTextViewU.text =
                "U-next の稼働時間: $hoursU 時間 $minutesU 分　(週)\nみんなの平均利用時間:\n評価\nコメント"
            val usageTimeTextViewDi: TextView = findViewById(R.id.hiddenMessageDi)
            val packageNameDi = "com.disney.disneyplus"
            val usageTimeDi = getAppUsageTime(packageNameDi)
            val hoursDi = (usageTimeDi / (1000 * 60 * 60)) + (usageTimeDi / (1000 * 60)) / 60
            val minutesDi = (usageTimeDi / (1000 * 60)) % 60
            usageTimeTextViewDi.text =
                "Disney+ の稼働時間: $hoursDi 時間 $minutesDi 分　(週)\nみんなの平均利用時間:\n評価\nコメント"
            val usageTimeTextViewDa: TextView = findViewById(R.id.hiddenMessageDa)
            val packageNameDa = "com.dazn"
            val usageTimeDa = getAppUsageTime(packageNameDa)
            val hoursDa = (usageTimeDa / (1000 * 60 * 60)) + (usageTimeDa / (1000 * 60)) / 60
            val minutesDa = (usageTimeDa / (1000 * 60)) % 60
            usageTimeTextViewDa.text =
                "Dazn の稼働時間: $hoursDa 時間 $minutesDa 分　(週)\nみんなの平均利用時間:\n評価\nコメント"
            val usageTimeTextViewA: TextView = findViewById(R.id.hiddenMessageA)
            val packageNameA = "com.apple.android.music"
            val usageTimeA = getAppUsageTime(packageNameA)
            val hoursA = (usageTimeA / (1000 * 60 * 60)) + (usageTimeA / (1000 * 60)) / 60
            val minutesA = (usageTimeA / (1000 * 60)) % 60
            usageTimeTextViewA.text =
                "Apple music の稼働時間: $hoursA 時間 $minutesA 分　(週)\nみんなの平均利用時間:\n評価\nコメント"

        }
        //各サブスクのボタンを定義
        val button1 = findViewById<Button>(R.id.netflix)
        //ボタンを押すとメッセージが出る
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
    //統計情報へのアクセス権限の有無を確認
    private fun hasUsageStatsPermission(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }
    //アプリの使用時間を取得
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
    //損得を計算する関数を定義する
    fun calculateLossAndProfit(Ave: Double, x: Double, a: Double): Pair<Double, Double> {
        return if (x >= Ave) {
            val profit = (x - Ave) / Ave * a /7// 平均利用時間を超えた分の利益を計算
            Pair(0.0, profit)  // 損失はゼロ、利益を返す
        } else {
            val usageRate = x / Ave
            val loss = a/7 * (1 - usageRate) // 平均以下の使用時間の割合分の損失を計算
            Pair(loss, 0.0)  // 損失と利益を返す
        }
    }
    //サブスクの利用状況に応じてメッセージを出力する
    fun generateSubscriptionMessage(
        averageUsage: Double, // 平均利用時間
        userUsage: Double, // ユーザの利用時間
        subscriptionCost: Double // サブスクの費用
    ): String {
        return if (userUsage > averageUsage) {
            "素晴らしい！利益が出ています。"
        } else {
            "解約検討かも。"
        }
    }
    //  ファイルをサーバに送信する関数(未完成)
    fun uploadFileToServer(file: File, serverUrl: String) {
        val boundary = "----WebKitFormBoundary" + System.currentTimeMillis()
        val lineEnd = "\r\n"
        val twoHyphens = "--"
        val url = URL(serverUrl)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=$boundary")
        connection.doInput = true
        connection.doOutput = true
        connection.useCaches = false
        DataOutputStream(connection.outputStream).use { outputStream ->
            outputStream.writeBytes(twoHyphens + boundary + lineEnd)
            outputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"${file.name}\"$lineEnd")
            outputStream.writeBytes("Content-Type: text/csv$lineEnd")
            outputStream.writeBytes(lineEnd)
            FileInputStream(file).use { fileInputStream ->
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (fileInputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
            }
            outputStream.writeBytes(lineEnd)
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd)
            outputStream.flush()
        }
        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
                val response = reader.readText()
                println("Server response: $response")
            }
        } else {
            println("File upload failed. Response code: $responseCode")
        }
        connection.disconnect()
    }
    //データをcsvに変換する関数(未完成)
    fun saveUsageStatsAsCSV(context: Context, startTime: Long, endTime: Long) {
        // UsageStatsManagerからデータ取得
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val usageStatsList: List<UsageStats> = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY, startTime, endTime
        )

        // CSV ファイル用のヘッダー
        val header = "Package Name,Start Time,End Time,Total Time in Foreground\n"

        // CSV ファイルの保存先ディレクトリ
        val csvFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "usage_stats.csv")

        try {
            FileWriter(csvFile).use { writer ->
                writer.append(header)

                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                for (usageStats in usageStatsList) {
                    val packageName = usageStats.packageName
                    val start = dateFormat.format(Date(usageStats.firstTimeStamp))
                    val end = dateFormat.format(Date(usageStats.lastTimeStamp))
                    val totalTime = usageStats.totalTimeInForeground / 1000 // 秒単位に変換

                    writer.append("$packageName,$start,$end,$totalTime\n")
                }
            }
            println("CSVファイルが正常に保存されました: ${csvFile.absolutePath}")
        } catch (e: IOException) {
            e.printStackTrace()
            println("CSVファイルの保存に失敗しました")
        }
    }

}