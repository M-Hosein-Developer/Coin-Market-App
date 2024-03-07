package com.example.coinmarket.ui.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.coinmarket.R
import com.example.coinmarket.ui.theme.BlurWhite
import com.example.coinmarket.ui.theme.TextBlack
import com.example.coinmarket.ui.theme.introTextColor
import com.example.coinmarket.viewModel.SignInViewModel

@Composable
fun SignInScreen(navController: NavHostController, viewModel: SignInViewModel) {

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            Modifier
                .fillMaxSize()
                .blur(12.dp),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(R.drawable.intro2),
                contentDescription = null,
                Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }



        Column(
            Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(18.dp))
                .background(BlurWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            SignInEmail(
                viewModel.email.value,
                Icons.Default.Email,
                "Enter Your Email"
            ){
                viewModel.email.value = it
            }

            SignInPassword(
                viewModel.password.value,
                Icons.Default.Lock,
                "Enter Your Password"
            ){
                viewModel.password.value = it
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxHeight(0.07f)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(introTextColor)
            ) {
                Text(text = "SIGN In")
            }

        }
    }



}

@Composable
fun SignInEmail(edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit) {
    OutlinedTextField(
        label = { Text(hint, color = TextBlack) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint, color = TextBlack) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        shape = ShapeDefaults.Medium,
        leadingIcon = { Icon(imageVector = icon, contentDescription = null, tint = TextBlack) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TextBlack,
            unfocusedBorderColor = TextBlack,
            focusedTextColor = TextBlack,
            focusedSupportingTextColor = TextBlack,
            cursorColor = TextBlack,
            unfocusedTrailingIconColor = Color.Black
        )
    )

}

@Composable
fun SignInPassword(edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        shape = ShapeDefaults.Medium,
        leadingIcon = { Icon(imageVector = icon, contentDescription = null, tint = TextBlack) },

    )
}

