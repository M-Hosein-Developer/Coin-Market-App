package com.example.coinmarket.ui.feature

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.coinmarket.R
import com.example.coinmarket.ui.theme.introTextColor
import com.example.coinmarket.util.MyScreens
import com.example.coinmarket.viewModel.SignInUpViewModel

@Composable
fun SignUpScreen(
    viewModel: SignInUpViewModel,
    navController: NavHostController,
    onSignUpClicked: () -> Unit
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
                .clip(RoundedCornerShape(18.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(R.string.signup),
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 8.dp , bottom = 14.dp)
            )
            
            SignUpEmail(
                viewModel.signUpEmail.value,
                Icons.Default.Email,
                stringResource(R.string.enter_email_text)
            ) {
                viewModel.signUpEmail.value = it
            }

            SignUpPassword(
                viewModel.signUpPassword.value,
                Icons.Default.Lock,
                stringResource(R.string.enter_email_text)
            ) {
                viewModel.signUpPassword.value = it
            }

            SignUpPassword(
                viewModel.signUpConfirmPassword.value,
                Icons.Default.Lock,
                stringResource(R.string.enter_conf_pass_text)
            ) {
                viewModel.signUpConfirmPassword.value = it
            }

            Button(
                onClick = {

                    if (
                        viewModel.signUpEmail.value.isNotEmpty() &&
                        viewModel.signUpPassword.value.isNotEmpty() &&
                        viewModel.signUpConfirmPassword.value.isNotEmpty()
                        ){

                        if ( viewModel.signUpPassword.value == viewModel.signUpConfirmPassword.value){

                            onSignUpClicked.invoke()

                        }else{

                            Toast.makeText(context, "Password Are Not The same", Toast.LENGTH_SHORT).show()

                        }

                    }else{
                        Toast.makeText(context, "Please Complete Field...", Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier
                    .padding(top = 24.dp, start = 18.dp, end = 18.dp)
                    .height(42.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(introTextColor)
            ) {
                Text(text = stringResource(R.string.signin))
            }

            Row (
                Modifier.fillMaxHeight(0.1f),
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(text = stringResource(R.string.have_account))

                TextButton(onClick = { navController.navigate(MyScreens.SignInScreen.route) }) {
                    Text(text = stringResource(R.string.login))
                }

            }

        }
    }

}

@Composable
fun SignUpEmail(edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
            .padding(vertical = 8.dp),
        shape = ShapeDefaults.Medium,
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        textStyle = TextStyle(
            color = Color.White
        )
    )

}

@Composable
fun SignUpPassword(edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
            .padding(vertical = 8.dp),
        shape = ShapeDefaults.Medium,
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        visualTransformation = PasswordVisualTransformation(),
        textStyle = TextStyle(
            color = Color.White
        )
    )
}