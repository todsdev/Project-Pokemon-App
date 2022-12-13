<!-- # Title -->
# Pokédex
![Demo](https://blog.logomyway.com/wp-content/uploads/2021/05/pokemon-logo-jpeg-1.jpg)


<!-- # Short Description -->

>- The user can check information for all the available **Pokémons**, including *moves*, *abilities*, *sprites* and much more
>- You can easily seach through the database and check any **Pokémon** you want and *favorite* it if you want
>- Check different informations about the **Pokémon** world, such as *move*, *abilities* and *type* explanations

The application was built mainly to studies purposes, but with a little bit of fan engagement as well. It search through all the official [Pokémon API](https://pokeapi.co),
requesting different kind of data. It displays a clean design but also contains a clean architecture.


<!-- # Badges -->
<div style="display: inline_block"><br>
    <img height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/androidstudio/androidstudio-original.svg">
    <img height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg">
    <img height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/firebase/firebase-plain.svg">
</div>

---

# Tags

`Android Studio` `Kotlin` `Firebase` `OkHTTP` `Coroutines` `MVVM` `Retrofit` `Hilt` `PokeAPI`

---


# Demo

<img height="400" width="400" src="https://media.discordapp.net/attachments/655489748885831713/1051192036562309130/map.png?width=902&height=1066">
>- Design of the navigation of the app. It displays how you can use the app dynamically from any layout 

![](https://media.discordapp.net/attachments/655489748885831713/1051192034427408444/opening.gif)
>- Opening with a small *Splash Activity* displaying the list of all **Pokémons**


![](https://media.discordapp.net/attachments/655489748885831713/1051192036251938846/listinteraction.gif)
>- You can freely navigate between from any **Pokémon** of the list, displaying a powerful personalized interface for each one


![](https://media.discordapp.net/attachments/655489748885831713/1051192035882831932/detailsinteraction.gif)
>- Starting from the details of a **Pokémon** you can navigate throught all the app information, as the map above shows

![](https://media.discordapp.net/attachments/655489748885831713/1051192035480186991/searchinteraction.gif)
>- You can search through all the **Pokémon list** available and with just a fragment of the name, find the one you want

![](https://media.discordapp.net/attachments/655489748885831713/1051192035178188830/typesinteraction.gif)
>- Show the information about all the **Pokémon Types**, displaying the available *Moves* and *Pokémons* for the chosen type, also displayins personalized 
layouts for each one

![](https://media.discordapp.net/attachments/655489748885831713/1051192034775531530/favoriteinteraction.gif)
>- Find your favorite **Pokémons** easily, adding or removing them anywhere you want


---

# Code Example
```kotlin
private fun configDataCollector() = lifecycleScope.launch {
        viewModel.search.collect { result ->
            binding.progressSearchPokemon.hide()
            when(result) {
                is ResourceState.Success -> {
                    binding.progressSearchPokemon.hide()
                    result.data?.let { values ->
                        val filteredList = mutableListOf<ResultsModel>()
                        val list: List<ResultsModel> = values.results.toList()
                        if(search.isNotEmpty()) {
                            for(i in list.indices) {
                                if(list[i].name.contains(search)) {
                                    filteredList.add(list[i])
                                }
                            }
                        } else {
                            toast(getString(R.string.an_error_occurred))
                        }
                        pokemonAdapter.pokemons = filteredList.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressSearchPokemon.hide()
                    result.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("SearchPokemonFragment").e("Error: $message")
                    }
                }
                is ResourceState.Loading -> { }
                else -> { }
            }
        }
    }
```

Since [Pokémon API](https://pokeapi.co) does not supply and search *end point*, in order to create a a search mechanism I needed to recover all the information
from the API, something about 1200 **Pokémons**. With all those names in a list, I had to receive the string the user requested as a seach and compare
if this small sequence contains in any items. If this small character sequence is identified, it is placed in another list and this second one, which is sent 
to adapter and it is displayed for user. \
E.g.: \
search -> "**saur**"  \
result -> "**bulbasaur**", "**ivysaur**", "**venusaur**"...

---

# Libraries

>- [Timber](https://github.com/JakeWharton/timber)
>- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
>- [Coroutines](https://developer.android.com/kotlin/coroutines?hl=pt-br)
>- [KTX](https://developer.android.com/kotlin/ktx)
>- [Retrofit](https://square.github.io/retrofit/)
>- [OkHTTP](https://square.github.io/okhttp/)
>- [Navigation Components](https://developer.android.com/guide/navigation)
>- [Hilt](https://dagger.dev/hilt/)
>- [Picasso](https://github.com/square/picasso)
>- [Picasso Palette](https://github.com/florent37/PicassoPalette)
>- [Carousel View](https://github.com/sayyam/carouselview)
>- [ROOM](https://developer.android.com/jetpack/androidx/releases/room?hl=pt-br)
>- [Google Firebase App Distribution](https://firebase.google.com)
>- [Pokémon API](https://pokeapi.co)

---

# Contributors

- [Thiago Rodrigues](https://www.linkedin.com/in/tods/)
