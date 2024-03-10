package com.example.coinmarket.ui.feature

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.coinmarket.R
import com.example.coinmarket.ui.theme.BlurBlue
import com.example.coinmarket.ui.theme.TextBlack
import com.example.coinmarket.ui.theme.introTextColor
import com.example.coinmarket.util.MyScreens
import com.example.coinmarket.viewModel.SignInUpViewModel

@Composable
fun SignInScreen(
    viewModel: SignInUpViewModel,
    navController: NavHostController,
    onSignInClicked: () -> Unit
) {

    val context = LocalContext.current

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
                .wrapContentSize()
                .clip(RoundedCornerShape(18.dp))
                .background(BlurBlue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            SignInEmail(
                viewModel.signInEmail.value,
                Icons.Default.Email,
                "Enter Your Email"
            ){
                viewModel.signInEmail.value = it
            }

            SignInPassword(
                viewModel.signInPassword.value,
                Icons.Default.Lock,
                "Enter Your Password"
            ){
                viewModel.signInPassword.value = it
            }

            Button(
                onClick = {

                    if (
                        viewModel.signInEmail.value.isNotEmpty() &&
                        viewModel.signInPassword.value.isNotEmpty()
                    ){
                        onSignInClicked.invoke()
                    }else{
                        Toast.makeText(context, "Please Complete Field...", Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier
                    .padding(top = 24.dp , start = 18.dp , end = 18.dp)
                    .fillMaxHeight(0.07f)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(introTextColor)
            ) {
                Text(text = "SIGN In")
            }

            Row (
                Modifier.fillMaxHeight(0.1f),
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(text = "Don`t have an account?")
                
                TextButton(onClick = { navController.navigate(MyScreens.SignUpScreen.route) }) {
                    Text(text = "Register Here")
                }

            }

        }
    }



}

@Composable
fun SignInEmail(edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(hint, color = TextBlack) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint, color = TextBlack) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp).padding(vertical = 8.dp),
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp).padding(vertical = 8.dp),
        shape = ShapeDefaults.Medium,
        leadingIcon = { Icon(imageVector = icon, contentDescription = null, tint = TextBlack) },
        visualTransformation = PasswordVisualTransformation(),
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
