# Copyright 2020 NXP

SUMMARY = "NXP i.MX Security Middleware Library"
DESCRIPTION = "NXP i.MX Security Middleware Library"
SECTION = "base"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=8636bd68fc00cc6a3809b7b58b45f982"

DEPENDS = "imx-seco-libs optee-os optee-client python3-pycryptodomex-native"

SRCBRANCH = "imx_5.4.70_2.3.0"
SMW_LIB_SRC ?= "git://source.codeaurora.org/external/imx/imx-smw.git;protocol=https"
SRC_URI = "${SMW_LIB_SRC};branch=${SRCBRANCH}"
SRCREV = "0321ad8ab115a7757de17deb17d27be437fe5844"

S = "${WORKDIR}/git"

inherit cmake python3native

OPTEE_OS_TA_EXPORT_DIR_aarch64 = "${STAGING_INCDIR}/optee/export-user_ta_arm64"
OPTEE_OS_TA_EXPORT_DIR_arm = "${STAGING_INCDIR}/optee/export-user_ta_arm32"

EXTRA_OECMAKE = " \
    -DSECO_ROOT=${STAGING_INCDIR} \
    -DTA_DEV_KIT_ROOT=${OPTEE_OS_TA_EXPORT_DIR} \
    -DTEEC_ROOT=${STAGING_DIR_HOST} \
    -DBUILD_TEST=ON \
    -DJSONC_ROOT="${COMPONENTS_DIR}/${TARGET_ARCH}/json-c/usr" \
"

PACKAGES =+ "${PN}-tests"

FILES_${PN} += "${base_libdir}/optee_armtz/*"

FILES_${PN}-tests = "${bindir}/* ${datadir}/${BPN}/tests/*"

RDEPENDS_${PN}-tests += " \
    json-c \
    bash \
    cmake \
"

COMPATIBLE_MACHINE = "(mx8)"
COMPATIBLE_MACHINE_mx8m = "(^$)"
