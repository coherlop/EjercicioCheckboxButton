package com.example.int_ejerciciocheckboxbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.int_ejerciciocheckboxbutton.ui.theme.Int_ejercicioCheckboxButtonTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

/*1. Un botón con el texto 'Presionar' que, al hacer clic, actualizará el mensaje en el campo de texto
 con 'Botón presionado' y mostrará un `CircularProgressIndicator` durante 5 segundos.

2. Un campo de texto que mostrará un mensaje, inicialmente no visible.

3. Una casilla de verificación (checkbox) con el texto 'Activar' que, al marcarla, mostrará el Text anterior.

4. Un icono de tu elección que se mostrará siempre en la interfaz.

5. Un interruptor (switch) que mostrará en grupo de botones siguiente(punto 6).

6. Un grupo de botones de radio (radiobutton) con al menos tres opciones distintas que permitirá al
usuario seleccionar una opción y actualizar el mensaje del campo de texto en consecuencia.

7. Una imagen que se actuaizará al hacer clic en el botón. La imagen puede cambiar entre al menos tres imágenes diferentes.*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Int_ejercicioCheckboxButtonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    miApp()
                }
            }
        }
    }
}

@Composable
fun miApp() {
    Column (modifier = Modifier.fillMaxSize()){
        Row (modifier = Modifier
            .weight(0.5f)
            .fillMaxWidth()
            .padding(5.dp),
            horizontalArrangement = Arrangement.Center)
        {
            icono()
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            horizontalArrangement = Arrangement.Center)
        {
            botonAnimado()
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            horizontalArrangement = Arrangement.Center)
        {
            checkbox()
        }
        Row (modifier = Modifier
            .fillMaxSize()
            .weight(3f),
            horizontalArrangement = Arrangement.Center)
        {
            Switch()
        }
        Row (modifier = Modifier
            .fillMaxSize()
            .weight(1f),
            horizontalArrangement = Arrangement.Center)
        {
            ImageSwitcher()
        }
    }
}


@Composable
fun icono(){
    Icon(modifier = Modifier.fillMaxWidth().height(50.dp).padding(5.dp),
        imageVector = Icons.Rounded.Star,
        contentDescription = "Icon",
        tint = Color.Magenta)
}

@Preview
@Composable
fun boton() {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .padding(10.dp)
                .weight(2f),
            Arrangement.Center
            ) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally){

                var text by remember {mutableStateOf("Presionar")}
                var loading by remember { mutableStateOf(false) }

                Button(onClick = {
                    text = "Botón presionado";
                    loading = !loading},
                    modifier = Modifier.padding(10.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            if (loading) {
                                circularLoading()
                                text = "Botón presionado"
                            }

                            Text(
                                text = text,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                }
            }

        }

    }
}

//-----------------------------INTENTO DE ANIMACION BIEN CON LISTENER----------------------------------------
@Composable
fun botonAnimado() {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .padding(10.dp)
                .weight(2f),
            Arrangement.Center
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally){

                var textoBoton by remember {mutableStateOf("Presionar")}
                var loading by remember { mutableStateOf(false) }

                Button(onClick = {
                    textoBoton = "Botón presionado";
                    loading = !loading},
                    modifier = Modifier.padding(10.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        if (loading) {
                            circularLoading()

                            LaunchedEffect(loading) {
                                delay(5.seconds)
                                textoBoton = "Presionar"
                            }
                        }

                        Text(
                            text = textoBoton,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }

        }

    }
}

//-------------------------------------------------------------------------------------------------------

@Composable
fun circularLoading() {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        val infiniteTransition = rememberInfiniteTransition()  //que sea infinita ?

        val progressAnimationValue by infiniteTransition.animateFloat(
            initialValue = 0.0f, //lo que está de lleno el dibujito al empezar la animacion
            targetValue = 1f, //lo que se llena (referencia)
            animationSpec = infiniteRepeatable(animation = tween(1000)), //lo que tarda en llenarse
            label = "circle loading" //nombre de la animacion
        )

        CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier.size(ButtonDefaults.IconSize),
            progress = progressAnimationValue,
            strokeWidth = 5.dp
        )
    }

    LaunchedEffect(Unit) {
        delay(5.seconds)
        isVisible = false
    }
}

@Composable
fun botonPresionado(){
    var isVisible by remember { mutableStateOf(true) }

    Text(
        text = "Botón presionado",
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )

    LaunchedEffect(Unit) {
        delay(5.seconds)
        isVisible = false
    }
}

@Composable //luis
fun checkbox(){
    var estado by rememberSaveable {mutableStateOf(ToggleableState.Off)}

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        TriStateCheckbox(state = estado,
            onClick = {
                estado = when(estado){
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.On -> ToggleableState.Off
                    ToggleableState.Indeterminate -> ToggleableState.On
                }
            })
        if(estado == ToggleableState.On){
            Text(text = "Ha marcado la casilla")
        }
    }
}

@Composable
fun radioButtonGroup() {
    val radioOptions = listOf("Opción A", "Opción B", "Opción C")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
    Column{
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ){
                Text(
                    text = "Ha seleccionado: " + selectedOption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun Switch() {
    val checkedState = remember { mutableStateOf(true) }

    Column {
        Row(Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.Center){
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
        }
        Row(Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.Center){
            if(checkedState.value) {
                radioButtonGroup()
        }

        }
    }
}


@Composable
fun ImageSwitcher() {
    val images = listOf(
        painterResource(R.drawable.lemon_122986),
        painterResource(R.drawable.cantaloupe_122982),
        painterResource(R.drawable.coconut_122981),
        painterResource(R.drawable.cherry_122983)
    )

    var currentImageIndex by remember { mutableStateOf(0) }

    val currentImage = images[currentImageIndex]

    Row(
        modifier = Modifier
            .clickable {
                currentImageIndex = (currentImageIndex + 1) % images.size
            }
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = currentImage,
            contentDescription = "Image",
            modifier = Modifier.height(200.dp).width(80.dp).padding(10.dp)
        )
    }
}








