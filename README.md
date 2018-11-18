Design Details:
1. I have used MVVM design pattern here for scalability, maintainability and to provide good code coverage.
2. Followed SOLID design principles 
3. Develop most of the modules(UI or non-UI) generic for scalability

Prerequisites:
1. Android studio 3.0 and above
2. Android API level 28
3. GIT

Setup: Source Code: https://github.com/Diwakar1988/sample-wallet-app
1. clone code from Github
2. open project in android studio
3. Sync project and follow instructions

Improvements:
We can still improve in below aspects which I didn't added due to time constraint 
1. We can use ConstraintLayout on most of the places
2. We can add more base ui components
3. We can make generic styles for TextView, EditText etc. as per brand specs
4. We can save user data(authenticate silently after first login) to avoid launching login screen on every app launch.
5. Suggestions are welcome :)

TODO:
1. Add test cases
2. Add MVVM pattern on missing screens
3. Fix UI issues like spacing, margin, font, text size etc

Thanks to 3rd Party libs:
I have used some third party libraries(we can develop our own libs) for fast ui development which are inline below:
1. OkHTTP			            https://github.com/square/okhttp
2. CircleImageView				https://github.com/hdodenhof/CircleImageView
3. GSON	                        https://github.com/google/gson

APK link: https://drive.google.com/drive/folders/19XO2NReYa8XeaiU_1g2G_tVH4d3E8ZEi?usp=sharing
