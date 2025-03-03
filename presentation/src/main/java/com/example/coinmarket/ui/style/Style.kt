package com.example.coinmarket.ui.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.coinmarket.R
import com.example.coinmarket.ui.theme.Green
import com.example.coinmarket.ui.theme.Red

object Style {

    val baseTextStyle = TextStyle(
        fontSize = Dimens.textSize_14Sp,
        fontFamily = FontFamily(Font(R.font.iransans)),
        fontWeight = FontWeight.Normal
    )

    //normal
    val tinyTextStyle = baseTextStyle.copy(fontSize = Dimens.textSize_12Sp)
    val xNormalTextStyle = baseTextStyle.copy(fontSize = Dimens.textSize_16Sp)
    val largeTextStyle = baseTextStyle.copy(fontSize = Dimens.textSize_18Sp)
    val veryLargeTextStyle = baseTextStyle.copy(fontSize = Dimens.textSize_28Sp)
    val XXLargeTextStyle = baseTextStyle.copy(fontSize = Dimens.textSize_34Sp)
    val XXXLargeTextStyle = baseTextStyle.copy(fontSize = Dimens.textSize_42Sp)

    //bold
    val normalBoldTextStyle = baseTextStyle.copy(fontWeight = FontWeight.Bold)
    val tinyBoldTextStyle = normalBoldTextStyle.copy(fontSize = Dimens.textSize_12Sp)
    val xNormalBoldTextStyle = normalBoldTextStyle.copy(fontSize = Dimens.textSize_16Sp)
    val largeBoldTextStyle = normalBoldTextStyle.copy(fontSize = Dimens.textSize_18Sp)
    val veryLargeBoldTextStyle = normalBoldTextStyle.copy(fontSize = Dimens.textSize_24Sp)
    val xLargeBoldTextStyle = normalBoldTextStyle.copy(fontSize = Dimens.textSize_20Sp)
    val XXLargeBoldTextStyle = normalBoldTextStyle.copy(fontSize = Dimens.textSize_34Sp)
    val XXXLargeBoldTextStyle = normalBoldTextStyle.copy(fontSize = Dimens.textSize_42Sp)


    //light
    val normalLightTextStyle = baseTextStyle.copy(fontWeight = FontWeight.Light)
    val tinyLightTextStyle = normalLightTextStyle.copy(fontSize = Dimens.textSize_12Sp)
    val xNormalLightTextStyle = normalLightTextStyle.copy(fontSize = Dimens.textSize_16Sp)
    val largeLightTextStyle = normalLightTextStyle.copy(fontSize = Dimens.textSize_18Sp)
    val veryLargeLightTextStyle = normalLightTextStyle.copy(fontSize = Dimens.textSize_24Sp)


    //red
    val redTinyTextStyle = tinyTextStyle.copy(color = Red)
    val redNormalTextStyle = baseTextStyle.copy(color = Red)
    val redXNormalTextStyle = xNormalTextStyle.copy(color = Red)
    val redLargeTextStyle = largeTextStyle.copy(color = Red)
    val redVeryLargeTextStyle = veryLargeTextStyle.copy(color = Red)

    //bold red
    val redTinyBoldTextStyle = redTinyTextStyle.copy(fontWeight = FontWeight.Bold)
    val redNormalBoldTextStyle = redNormalTextStyle.copy(fontWeight = FontWeight.Bold)
    val redXNormalBoldTextStyle = redXNormalTextStyle.copy(fontWeight = FontWeight.Bold)
    val redLargeBoldTextStyle = redLargeTextStyle.copy(fontWeight = FontWeight.Bold)
    val redVeryLargeBoldTextStyle = redVeryLargeTextStyle.copy(fontWeight = FontWeight.Bold)

    //green
    val greenTinyTextStyle = tinyTextStyle.copy(color = Green)
    val greenNormalTextStyle = baseTextStyle.copy(color = Green)
    val greenXNormalTextStyle = xNormalTextStyle.copy(color = Green)
    val greenLargeTextStyle = largeTextStyle.copy(color = Green)
    val greenVeryLargeTextStyle = veryLargeTextStyle.copy(color = Green)

    //bold green
    val greenTinyBoldTextStyle = greenTinyTextStyle.copy(fontWeight = FontWeight.Bold)
    val greenNormalBoldTextStyle = greenNormalTextStyle.copy(fontWeight = FontWeight.Bold)
    val greenXNormalBoldTextStyle = greenXNormalTextStyle.copy(fontWeight = FontWeight.Bold)
    val greenLargeBoldTextStyle = greenLargeTextStyle.copy(fontWeight = FontWeight.Bold)
    val greenVeryLargeBoldTextStyle = greenVeryLargeTextStyle.copy(fontWeight = FontWeight.Bold)

    //white
    val whiteTinyTextStyle = tinyTextStyle.copy(color = Color.White)
    val whiteNormalTextStyle = baseTextStyle.copy(color = Color.White)
    val whiteXNormalTextStyle = xNormalTextStyle.copy(color = Color.White)
    val whiteLargeTextStyle = largeTextStyle.copy(color = Color.White)
    val whiteVeryLargeTextStyle = veryLargeTextStyle.copy(color = Color.White)
    val whiteXXLargeTextStyle = XXLargeTextStyle.copy(color = Color.White)
    val whiteXXXLargeTextStyle = XXXLargeTextStyle.copy(color = Color.White)

    //bold white
    val whiteTinyBoldTextStyle = whiteTinyTextStyle.copy(fontWeight = FontWeight.Bold)
    val whiteNormalBoldTextStyle = whiteNormalTextStyle.copy(fontWeight = FontWeight.Bold)
    val whiteXNormalBoldTextStyle = whiteXNormalTextStyle.copy(fontWeight = FontWeight.Bold)
    val whiteLargeBoldTextStyle = whiteLargeTextStyle.copy(fontWeight = FontWeight.Bold)
    val whiteVeryLargeBoldTextStyle = whiteLargeTextStyle.copy(fontWeight = FontWeight.Bold)
    val whiteXXLargeBoldTextStyle = whiteXXLargeTextStyle.copy(fontWeight = FontWeight.Bold)

    //light
    val whiteNormalLightTextStyle = normalLightTextStyle.copy(color = Color.White)
    val whiteXNormalLightTextStyle = xNormalLightTextStyle.copy(color = Color.White)


}