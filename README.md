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
![](https://user-images.githubusercontent.com/90802279/172026916-a617134b-a829-4d67-b8ec-ef10ea4be266.png)
![](https://img.shields.io/github/v/release/Sorenon/MCXR) ![](https://img.shields.io/discord/829825443480993802?label=discord) ![](https://img.shields.io/modrinth/dt/hcEWWGik?label=mcxr-core%20downloads) ![](https://img.shields.io/modrinth/dt/9jVyqpHR?label=mcxr-play%20downloads) ![](https://img.shields.io/reddit/subreddit-subscribers/MinecraftXR)
<br/>A virtual reality / augmented reality mod for Minecraft using OpenXR and Fabric.

**Socials**
> [Discord](https://discord.gg/a7PPU9tYDU)
> <br/>[Reddit](https://www.reddit.com/r/MinecraftXR)
> <br/>[Modrinth](https://modrinth.com/user/Sorenon)

**Information**
> [VR Installation](#installation-for-vr-players)
> <br/>[Non-VR and Server Installation](#installation-for-non-vr-players-and-servers)
> <br/>[Comparing Competition](#comparisons-to-competitors)

---------
### Installation for VR Players
Download the latest version of [MCXR Core](https://modrinth.com/mod/mcxr-core) & [MCXR Play](https://modrinth.com/mod/mcxr-play). Then, install the mods by placing them into the /mods folder.
<br/>
In order to access the title screen within Minecraft XR, use [Titleworlds](https://modrinth.com/mod/titleworlds).

### Installation for Non-VR Players and Servers
Download the latest version of [MCXR Core](https://modrinth.com/mod/mcxr-core). Then, install the mod by placing it into the /mods folder.

### *Comparisons to Competitors*
> ✅ *YES*
> <br/>⛔ *NO*
> <br/>🚧 *IN DEVELOPMENT*

| Comparisons                 | [Bedrock VR](https://www.minecraft.net/en-us/vr)| [Vivecraft](http://www.vivecraft.org/)| [Minecraft XR](https://github.com/Sorenon/MCXR) |
| --------------------------- | ------------------------------- | ----------------------------- | ---------------------------- |
| State                       | Released                        | Released                      | Beta                         |
| Release Year                | 2016                            | 2013 / 2016                   | 2021 (Beta)                  |
| Platform                    | Bedrock                         | Java                          | Java                         |
| Mod Support                 | Bedrock Add-Ons                 | Forge                         | Fabric, Quilt                |
| Performance                 | 🥇                              | 🥉                           | 🥈 (with Sodium)             |
| Supported Devices           | 🟡 Oculus and WMR Headsets      | ✅ OpenVR-supported Devices   | ✅ OpenXR-supported Devices |
| Shaders Support             | ⛔                              | ✅ Optifine                   | ✅ Iris                     |
| Vanilla Server Compatibility| ✅                              | ✅                           | ✅                           |
| VR Addon Support            | ⛔                              | 🚧                           | 🚧                           |
| 3rd party Mod Intergration  | ⛔                              | ⛔                           | 🚧                           |
| Immersive UI                | ⛔                              | ⛔                           | 🚧                           |
| Immersive Controls          | ⛔                              | ✅                           | 🚧                           |
| Open Source                 | ⛔                              | ✅                           | ✅                           |
| Versions                    | Latest                          | 1.7.10, 1.10 to 1.18          | 1.18                         |
| Standalone Quest Support    | ⛔                              | ⛔                           | ✅ [Questcraft](https://sidequestvr.com/app/7150/questcraft)|
| VR Playermodels             | ⛔                              | ✅                           | 🚧                           |
| Spigot Plugin Support       | ⛔                              | ✅                           | ⛔                           |
| Mod Method                  | Built-in                        | Jar mod                       | Mixins                       |
| Sponsored by Facebook       | Yes                              | No                           | No                           |
| Linux Support               | ⛔                              | ✅                           | ✅                           |
| Mac Support                 | ⛔                              | ⛔                           | ⛔                           |
