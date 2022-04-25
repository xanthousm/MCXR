This fork of MCXR contains tweaks to the mod that I just couldn't wait for - so I did them myself. I am testing my builds on a Quest 2 using virtual desktop to link to Windows 10, so the controller mappings for other VR device controllers might need improvement. The current controller mappings are as follows:
## *Control Mappings*
## Oculus/Vive Cosmos/HP Reverb G2
|Action|Left|Right|
|---|---|---|
|…/input/a/click||jump|
|…/input/b/click||quick menu/chat|
|…/input/x/click|teleport||
|…/input/y/click|inventory/menu(hold)||
|…/input/menu/click|menu||
|…/input/squeeze/value|hotbar|hotbar|
|…/input/trigger/value|use|attack|
|…/input/thumbstick/x|move|turn|
|…/input/thumbstick/y|move|sprint/sneak|
|…/input/thumbstick/click|swap off-hand|reset pos+change height|

## Index
|Action|Left|Right|
|---|---|---|
|…/input/a/click||jump|
|…/input/b/click|inventory/menu(hold)||
|…/input/squeeze/value|hotbar|hotbar|
|…/input/trigger/value|use|attack|
|…/input/thumbstick/x|move|turn|
|…/input/thumbstick/y|move|sprint/sneak|
|…/input/thumbstick/click|swap off-hand|reset pos+change height|
|…/input/trackpad/x|||
|…/input/trackpad/y|||
|…/input/trackpad/force|teleport|quickmenu/chat|

## Vive Wands
|Action|Left|Right|
|---|---|---|
|…/input/squeeze/click|hotbar|hotbar|
|…/input/menu/click|teleport|quickmenu/chat|
|…/input/trigger/value|use|attack|
|…/input/trackpad/x|move|turn|
|…/input/trackpad/y|move|sprint/sneak|
|…/input/trackpad/click|inventory/menu(hold)|jump|

## WMR
|Action|Left|Right|
|---|---|---|
|…/input/menu/click|teleport|quickmenu/chat|
|…/input/squeeze/click|hotbar|hotbar|
|…/input/trigger/value|use|attack|
|…/input/thumbstick/x|move|turn|
|…/input/thumbstick/y|move|sprint/sneak|
|…/input/thumbstick/click|swap off-hand|reset pos+change height|
|…/input/trackpad/x|||
|…/input/trackpad/y|||
|…/input/trackpad/click|inventory/menu(hold)|jump|

### Licences
net.sorenon.mcxr.play.rendering.MapRenderer: LICENCE-tr7zw

All other files: LICENCE

### Original Description
[![#MinecraftXR](https://user-images.githubusercontent.com/51373236/114272119-ad237800-9a0c-11eb-8786-6275555a594b.png)](#)

A VR mod for Minecraft using OpenXR and fabric.

# *Installation*
## For VR Players
Fetch the latest version of MCXR Core and Play from https://modrinth.com/mod/mcxr-play and https://modrinth.com/mod/mcxr-core and put them in the fabric mods folder. 
<br/>
For an in game title screen also install and set up a compatible version of https://github.com/Sorenon/TitleWorlds
## For Servers and Non-VR Players
MCXR is compatible with Vanilla servers and players but for the full experience install MCXR Core from https://modrinth.com/mod/mcxr-core

# *Known Issues*
Vive Pro controllers are not yet supported <br/>
No way to rebind controls without SteamVR</br>
SteamVR on Linux crashes due to poor OpenXR implementation, this can be mitigated with an [OpenXR Layer](https://github.com/Sorenon/sorenon_openxr_layer)

## *Related Links*
Join the Discord server [here.](https://discord.gg/fyBye2ptkS) <br/>
Alternatively, join the Subreddit [here.](https://www.reddit.com/r/MinecraftXR/)

## *Comparison Table*


|                             | [Official Minecraft VR](https://www.minecraft.net/en-us/vr)| [Vivecraft](http://www.vivecraft.org/)| [MCXR VR](https://github.com/Sorenon/MCXR) |
| --------------------------- | ------------------------------- | ----------------------------- | ---------------------------- |
| State                       | Released                        | Released                      | Beta                     |
| Platform                    | Bedrock                         | Java                          | Java                     |
| Mod Support                 | Bedrock Add-Ons                 | Forge                         | Fabric                   |
| Performance                 | 🥇                              | 🥉                            | 🥈 (With Sodium)          |
| Supported Devices           | 👎 Only Oculus headsets and WMR | ✅OpenVR supported devices     | ✅OpenXR supported devices|
| Pretty Shader Support       | ⛔                               | ✅Optifine                     | ✅Iris or 🚧Canvas      |
| Vanilla Server Compatibility| ✅                               | ✅                             | ✅                      |
| Vr Addon Support            | ⛔                               | 🚧In development             | 🚧In development          |
| Thirdparty Mod Intergration | N/A                             | ⛔                             | 🚧In development         |
| Immersive UI                | ⛔                               | ⛔                             | 🚧In development        |
| Immersive Controls          | ⛔                               | ✅                             | 🚧In development        |
| Open Source                 | ⛔                               | ✅                             | ✅                      |
| Versions                    | N/A                             | 1.7.10, and 1.10 through [1.18](https://github.com/jrbudda/Vivecraft_118/releases) | 1.18                     |
| Standalone Quest Support    | ⛔                               | ⛔                           | ✅[QuestCraft](https://sidequestvr.com/app/7150/questcraft)|
| Special VR player models in multiplayer| ⛔                    | ✅                             | 🚧In development        |
| Spigot Plugin               | N/A                             | ✅                             | ⛔                       |
| Patch Method                | N/A                             | Jar mod                       | Mixins                   |
| Sponsored by ~~Facebook~~ 'Meta'       | Yes                  | No                            | No                        |
| Initial Release Year        | 2016                            | 2013 / 2016                   | 2021                      |
| Linux Support               | ⛔                               | ✅                            | ✅                        |
| Mac Support                 | ⛔                               | ✅?                            | ⛔                       |
