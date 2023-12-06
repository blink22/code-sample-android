## **Application Architecture**
In Top Kotlins, we follow the **Clean Architecture + MVVM ** to build it. <br />
There are different opinions about how many layers Clean Architecture should have. The architecture doesn't define exact layers but instead lays out the foundation. 
The idea is that you adapt the number of layers to your needs.<br />

**In Top Kotlins Android, we divides it into 5 layers:** <br />
- **App Layer (App Package):** It contains Application's class and AppConstants.
- **Core Layer (Core Package):** Implements interaction with the Android SDK and provides concrete implementations for the data layer.<br />
- **Data Layer(Data Package):** Contains Application and business logic. It contains abstract definition of all the data sources. <br />
- **UseCases Layer (Domain Package):** Defines actions the user can trigger. The UI only interacts with data package through calls to useCases. <br />
- **UI Layer (UI Package):** A layer that interacts with the UI. <br />

Applying those layers:

- **App Package:** Contains app level usages.
- **Core Package:** Contains 2 packages:
    - **API Package**: contains all the network interfaces required by retorfit or any other client.
    - **Datasource Package:** Contains the implementation of the interfaces (Datasource interfaces) defined in the Data Layer.
    - **DI Package:** Contains authenticator, interceptors and injection providers.
    - **Repositories Package**: Contains repositories implementations defined in data package.
- **Data Package:** Contains 2 packages:
    - **Model Package**: contains all the models and business rules of your app.
    - **Repositories Package**: provides abstract definitions (Repository & Interfaces) for accessing data sources like a database and internet. Repository Pattern is used in this layer. The main purpose of the Repository Pattern is to abstract away the concrete implementation of data access.

- **Domain Package:** Contains usecases Encapsulates complex business logic that is used by viewModels.
- **UI Package:** Contains the user interface related code. It contains the views used (Fragments,Activities) along with the viewModels and any other view components.

## **Folder Structure**

### **1. App Package**
App package contains any app level files. i.e. as we use **Hilt DI** it requires that the application must have an Application class that is annotated with @HiltAndroidApp to trigger Hilt's code generation, including a base class for your application that serves as the application-level dependency container.

📦 app</br>
┣ 📜 AppConstants.kt</br>
┗ 📜 TopKotlinsApp.kt</br>

---
### **2. Core Package**
Core package implements interaction with the Android SDK (network calls, local database calls, etc. ). It provides all these functionalities by implementing the interfaces (Datasource interfaces) defined in the Data Layer as well as the DI package.

Also core package provides injection to bind interfaces defined in data layer with its implementation in core layer.

- **Core Package**: for each model, a package is created that contains:
    - API Interface.
    - RepositoryImpl Class.

📦 core</br>
┣ 📂 api</br>
┃ ┣ 📜 GithubRepoApi.kt</br>
┣ 📂 datasource</br>
┃ ┣ 📜 GithubRepoRemoteDataSource.kt</br>
┃ ┣ 📜 GithubRepoRemoteDataSourceImpl.kt</br>
┣ 📂 di</br>
┃ ┣ 📜 GithubRepoModule.kt</br>
┃ ┣ 📜 RetrofitNetworkModule.kt</br>
┣ 📂 repositories</br>
┃ ┗ 📜 GithubRepoRepositoryImpl.kt</br>

---

### **3. Data Package**
Data package contains all the code that doesn't depend on Android SDK(so it's a kotlin package). It will have the implementation of Data, Domain and Usecase layers.

- **Repositories Package**: for each model, a package is created that contains:
    - Repository Interface.

- **Models Package**: contains all entities files i.e. **BaseResponse** & **GithubRepo**.

📦 data</br>
┣ 📂 api</br>
┃ ┣ 📜 GithubRepoApi.kt</br>
┣ 📂 models</br>
┃ ┣ 📜 BaseResponse.kt</br>
┃ ┣ 📜 GithubRepo.kt</br>
┃ ┗ 📜 RepoOwner.kt</br>
┣ 📂 repositories</br>
┃ ┣ 📂 GithubRepo</br>
┃ ┃ ┗ 📜 GithubRepoRepository.kt</br>

 ---
 
### **4. UI Package**
UI package contains any UI related code. For this layer, we follow **MVVM Architecture**. Specifically, UI layer contains the **View** and **ViewModel** layers from MVVM architecture:

- **View**: It consists of the UI Code(Activity, Fragment), XML. It sends the user action to the ViewModel but does not get the response back directly. To get the response, it has to subscribe to the observables which ViewModel exposes to it.

- **ViewModel**: It is a bridge between the View and Model(business logic). It does not have any clue which View has to use it as it does not have a direct reference to the View. So basically, the ViewModel should not be aware of the view which is interacting with. It interacts with the Model and exposes the observable that can be observed by the View.

In the UI package, there are:
- **base package**: contains all base classes for Activities, Fragments and ViewModels.

- **views package**: for each new screen, create a sub-package that will have at least the fragment class that extends **BaseFragment** and viewModel class that extends **BaseViewModel**

- **utils package**: contains any YU utils classes, functions or extensions.

- **MainActivity Class**: the only activity in Top Kotlins App as we follow the philosophy of ***One Activity Multiple Fragments***.

📦 ui</br>
┣ 📂 base</br>
┃ ┣ 📜 BaseActivity.kt</br>
┃ ┣ 📜 BaseEvent.kt</br>
┃ ┣ 📜 BaseFragment.kt</br>
┃ ┣ 📜 BaseRecyclerViewAdapter.kt</br>
┃ ┗ 📜 BaseViewModel.kt</br>
┣ 📂 utils</br>
┣ ┃ ┗📜 Extensions.kt</br>
┣ 📂 views</br>
┃ ┣ 📂 home</br>
┃ ┃ ┣ 📜 HomeEvent.kt</br>
┃ ┃ ┣ 📜 HomeFragment.kt</br>
┃ ┃ ┗ 📜 HomeViewModel.kt</br>
┗ 📜 MainActivity.kt</br>
